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
	publishers{
		sonar{
			branch('master')
		}
	}
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
		git {
            remote {
                github('PascalStieber/SpringCommonDemo')
                credentials('9d7f5d5b-8cb8-4f14-af04-101e4417bb9b')
            }
            extensions{
            		cleanBeforeCheckout()
            }
        }
	}
	
	goals('release:clean release:prepare release:perform')
}
