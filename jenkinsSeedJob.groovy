mavenJob('buildSpringCommonDemo'){
	scm{
		github('PascalStieber/SpringCommonDemo')
	}
	goals('clean install')
}