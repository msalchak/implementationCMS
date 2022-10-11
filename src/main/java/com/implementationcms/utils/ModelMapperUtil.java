package com.implementationcms.utils;

import com.implementationcms.domain.response.DetailedAdminPostResponse;
import com.implementationcms.domain.response.DetailedCustomerPostResponse;
import com.implementationcms.domain.response.SimplePostResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtil {

    public static ModelMapper getModelMapper() {
        ModelMapper mapper =  new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.addMappings(DetailedAdminPostResponse.DetailedAdminPostDtoMap);
        mapper.addMappings(DetailedCustomerPostResponse.DetailedCustomerPostDtoMap);
        mapper.addMappings(SimplePostResponse.PostToSimplePostDtoMap);
        return mapper;
    }
}
