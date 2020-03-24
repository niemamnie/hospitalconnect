package de.hackathon.hospitalconnect.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class CopyService {


    public <T> T map(Object source, T target) {

        MapperFactory mapper = new DefaultMapperFactory.Builder().build();
        mapper.classMap(source.getClass(), target.getClass())
                .mapNulls(false)
                .exclude("id")
                .byDefault()
                .register();
        MapperFacade mapperFacade = mapper.getMapperFacade();

        mapperFacade.map(source, target);
        return target;
    }
}
