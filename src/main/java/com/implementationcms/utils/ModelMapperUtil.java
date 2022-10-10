package com.implementationcms.utils;

import com.implementationcms.application.response.DetailedAdminContentResponse;
import com.implementationcms.application.response.DetailedCustomerContentResponse;
import com.implementationcms.application.response.SimpleContentResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtil {

    public static ModelMapper getModelMapper() {
        ModelMapper mapper =  new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.addMappings(DetailedAdminContentResponse.DetailedAdminContentDtoMap);
        mapper.addMappings(DetailedCustomerContentResponse.DetailedCustomerContentDtoMap);
        mapper.addMappings(SimpleContentResponse.ContentToSimpleContentDtoMap);
        return mapper;
    }
}
