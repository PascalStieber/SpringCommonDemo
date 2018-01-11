mavenJob('buildSpringCommonDemo'){
	logRotator {
        numToKeep(5)
        artifactNumToKeep(1)
    }
    triggers{
    		pollSCM{scmpoll_spec('H/5 * * * *')}
    }
	scm{
		github('PascalStieber/SpringCommonDemo')
	}
	goals('clean install')
}

mavenJob('releaseSpringCommonDemo'){
	logRotator {
        numToKeep(5)
        artifactNumToKeep(1)
    }
    //triggers{
    	//	pollSCM{scmpoll_spec('H/30 * * * *')}
    //}
	scm{
		github('PascalStieber/SpringCommonDemo')
	}
	goals('release:prepare release:perform')
}
