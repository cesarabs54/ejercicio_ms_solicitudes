package co.com.bancolombia.model.gateway;

import java.io.Serializable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericGateway<T, I extends Serializable> {

    Mono<Void> deleteById(I id);

    Flux<T> findAll();

    Mono<T> findById(I id);

    Mono<T> save(T entity);

    Mono<T> update(T entity);

}
