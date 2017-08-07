package com.example.demo;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import org.junit.Ignore;
import org.springframework.http.MediaType;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.CharStreams;
import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Ignore
public final class ContentUrlRewritingFilter extends ZuulFilter {

    private static final String CONTENT_TYPE = "Content-Type";

    private static final ImmutableSet<MediaType> DEFAULT_SUPPORTED_TYPES = ImmutableSet.of(MediaType.APPLICATION_JSON);

    private final String regex;
    private final String replacement;
    private final ImmutableSet<MediaType> supportedTypes;

    public ContentUrlRewritingFilter(final String regex, final String replacement) {
        this(regex, replacement, DEFAULT_SUPPORTED_TYPES);
    }

    public ContentUrlRewritingFilter(final String regex, final String replacement, final Collection<MediaType> supportedTypes) {
        this.regex = checkNotNull(regex);
        this.replacement = checkNotNull(replacement);
        this.supportedTypes = ImmutableSet.copyOf(checkNotNull(supportedTypes));
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        final RequestContext context = RequestContext.getCurrentContext();
        return containsContent(context) && supportsType(context, this.supportedTypes);
    }

    private static boolean containsContent(final RequestContext context) {
        assert context != null;
        return context.getResponseDataStream() != null || context.getResponseBody() != null;
    }

    private static boolean supportsType(final RequestContext context, final Collection<MediaType> supportedTypes) {
        assert supportedTypes != null;
        return supportedTypes.contains(getResponseMediaType(context));
    }

    private static MediaType getResponseMediaType(final RequestContext context) {
        assert context != null;
        for (final Pair<String, String> header : context.getZuulResponseHeaders()) {
            if (header.first().equalsIgnoreCase(CONTENT_TYPE)) {
                return MediaType.parseMediaType(header.second());
            }
        }
        return MediaType.APPLICATION_OCTET_STREAM;
    }

    @Override
    public Object run() {
        try {
            rewriteContent(RequestContext.getCurrentContext(), this.regex, this.replacement);
        } catch (final Exception e) {
            Throwables.propagate(e);
        }
        return null;
    }

    private static void rewriteContent(final RequestContext context, final String regex, final String replacement) throws Exception {
        assert context != null;
        if (context.getResponseBody() != null) {
            context.getResponse().setCharacterEncoding("UTF-8");
            context.setResponseBody(context.getResponseBody().replaceAll(regex, replacement));
        } else if (context.getResponseDataStream() != null) {
            context.getResponse().setCharacterEncoding("UTF-8");
            try (final InputStream responseDataStream = context.getResponseDataStream()) {
                //FIXME What about character encoding of the stream (depends on the response content type)?
                final String responseData = CharStreams.toString(new InputStreamReader(responseDataStream));
                context.setResponseBody(responseData.replaceAll(regex, replacement));
            }
        } else {
            // there is no body to send
            return;
        }
    }
}