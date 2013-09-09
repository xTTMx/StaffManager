package com.github.SkyBirdSoar.Exceptions;

public class UnknownPermissionsManagerException extends IllegalArgumentException{
    public UnknownPermissionsManagerException(String permissionsmanager){
        super("Unknown Permissions Manager: " + permissionsmanager);
    }
}
