package com.nagalay.profileservice.common;

import com.nagalay.profileservice.dto.response.ProfileRest;
import com.nagalay.profileservice.entity.ProfileEntity;
import org.springframework.beans.BeanUtils;

public class EntityTransform {

    public static ProfileRest toProfileRest(ProfileEntity entity) {
        var res = new ProfileRest();
        BeanUtils.copyProperties(entity, res);
        return res;
    }

}
