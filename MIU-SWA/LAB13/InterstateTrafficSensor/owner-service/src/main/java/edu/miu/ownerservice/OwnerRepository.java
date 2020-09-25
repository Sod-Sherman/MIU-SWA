package edu.miu.ownerservice;

import kafka.Owner;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class OwnerRepository {

    private Map<String, Owner> ownerMap;

    public void setOwnerMap(Map<String, Owner> ownerMap) {
        this.ownerMap = ownerMap;
    }

    public Map<String, Owner> getOwnerMap() {
        return ownerMap;
    }
}
