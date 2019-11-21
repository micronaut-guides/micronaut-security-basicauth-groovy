package example.micronaut

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest // <1>
class BasicAuthClientSpec extends Specification {

    @Inject
    EmbeddedServer embeddedServer // <2>

    def "Verify HTTP Basic Auth works"() {
        when:
        AppClient appClient = embeddedServer.applicationContext.getBean(AppClient) // <3>

        then:
        noExceptionThrown() // <4>

        when:
        String credsEncoded = "sherlock:password".bytes.encodeBase64().toString()
        String rsp = appClient.home("Basic ${credsEncoded}") // <5>

        then:
        rsp == 'sherlock'
    }
}
