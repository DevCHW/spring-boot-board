package io.dodn.springboot.core.api.converter;

import io.dodn.springboot.core.api.support.error.CoreApiException;
import io.dodn.springboot.core.api.support.error.ErrorType;
import org.springframework.core.convert.converter.Converter;

import java.util.UUID;

public class MyConverter implements Converter<String, UUID> {

    @Override
    public UUID convert(String source) {
        try {
            System.out.println("여기 오냐??");
            return UUID.fromString(source);
        } catch (IllegalArgumentException e) {
            throw new CoreApiException(ErrorType.BAD_REQUEST);
        }
    }
}
