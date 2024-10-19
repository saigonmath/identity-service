package com.softz.identity_service.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPermissionRequest {
    private int id;
    private String name;
    private String description;
}
