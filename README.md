# OpenTelemetry demo
OpenTelemetry (Otel) demo project.
Tools: SpringBoot, Jaeger, Otel, Kafka

## How to run
```
git clone git@github.com:Oak10/OpenTelemetry.git
docker-compose up -d
```

## Flow/Messages
```
#Message Flow:
otel-uno --HTTP--> otel-dos --kafka--> otel-cuatro

# otel-uno api return:
WebMessageMain{
    message
    WebMessage{
        message
    }
}

# Message Flow (async call/ sync - with "2 spans"):
otel-uno --HTTP--> otel-dos
         --HTTP--> otel-cuatro
# otel-uno api return:
WebMessageMainDouble{
    message
    WebMessage{
        message
    }
    WebMessageTwo{
        message
    }
}

```

## Links ( "openapi :D")
### otel-uno
- ok (otel-uno <-http-> otel-dos):
http://localhost:8080/webmain/message/{id}
- ok (otel-uno <- http -> otel-dos <- kafka -> otel-cuatro)
http://localhost:8080/webmain/message/full-path
- error (throw in otel-uno)
http://localhost:8080/webmain/message/internal-throw
- error (in otel-dos):
http://localhost:8080/webmain/message/otel-dos/internal-error
- not_found (in otel-dos)::
http://localhost:8080/webmain/message/otel-dos/not-found
- 2 requests otel-dos with 2s delay 
http://localhost:8080/webmain/message/two-requests-with-delay
- 2 requests otel-dos with 2s delay (async)
http://localhost:8080/webmain/message/two-requests-with-delay-async

### otel-dos
- kafka message:
http://localhost:8081/kafka/produce
- ok:
http://localhost:8081/web/message/{id}
- notfound
http://localhost:8081/web/message/not-found
- internal_error:
http://localhost:8081/web/message/internal-error
- delay x seconds:
http://localhost:8081/web/message/delay/{seconds}
and
http://localhost:8081/web/message/delay-two/{seconds}

### Links (servers)
- jaeger:
http://localhost:16686/


## Refs / future work
- OpenTracing Prometheus/Grafana/Jaeger ex:
https://developers.redhat.com/blog/2017/07/10/using-opentracing-with-jaeger-to-collect-application-metrics-in-kubernetes#conclusion

- Service performance monitoring:
https://www.jaegertracing.io/docs/1.43/spm/
https://github.com/jaegertracing/jaeger/blob/main/docker-compose/monitor/docker-compose.yml

- KAfka:
https://www.baeldung.com/spring-kafka

- Debug KAfka:
https://www.confluent.io/blog/kafka-client-cannot-connect-to-broker-on-aws-on-docker-etc/

- Jaeger native support openTelemetry ( shim not needed anymore)
https://medium.com/jaegertracing/introducing-native-support-for-opentelemetry-in-jaeger-eb661be8183c


## Notes
- Different apis may have different results (otel shows different behaviors - e.g. Future and Spring Async annotation -)
- Bug on spring-kafka consumer with kafka hostname! (workaround - shows one more span)
