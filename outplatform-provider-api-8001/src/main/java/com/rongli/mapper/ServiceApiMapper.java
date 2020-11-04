package com.rongli.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.entities.params.ServiceEntity;

public interface ServiceApiMapper extends BaseMapper<ServiceEntity>{

	public ServiceEntity getServiceEntity(String serviceId);
}
