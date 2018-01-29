package com.var.software.dxf.dxf;

import com.var.software.dxf.dxf.entities.DxfEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DxfLayer {

    private Map<String, List<DxfEntity>> layers;

    public DxfLayer() {
        layers = new HashMap<>();
    }

    public Map<String, List<DxfEntity>> getLayers() {
        return layers;
    }

    public List<DxfEntity> getLayer(String key) {
        return layers.get(key);
    }

    public void addEntity(DxfEntity entity) {
        if (!layers.containsKey(entity.getLayerName())) {
            layers.put(entity.getLayerName(), new ArrayList<>());
        } else {
            getLayer(entity.getLayerName()).add(entity);
        }
    }
}
