package com.var.software.dxf.dxf;

import com.var.software.dxf.dxf.entities.DxfEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DxfLayer {

    private Map<String, Map<String, List<DxfEntity>>> layers;

    public DxfLayer() {
        layers = new HashMap<>();
    }

    public Map<String, Map<String, List<DxfEntity>>> getLayers() {
        return layers;
    }

    public Map<String, List<DxfEntity>> getLayer(String key) {
        return layers.get(key);
    }

    public void addEntity(DxfEntity entity) {
        if (!layers.containsKey(entity.getLayerName())) {
            Map<String, List<DxfEntity>> map = new HashMap<>();
            map.put(entity.getType(), new ArrayList<>());
            layers.put(entity.getLayerName(), map);
            layers.get(entity.getLayerName()).get(entity.getType()).add(entity);
        } else {
            if (!layers.get(entity.getLayerName()).containsKey(entity.getType())) {
                layers.get(entity.getLayerName()).put(entity.getType(), new ArrayList<>());
                layers.get(entity.getLayerName()).get(entity.getType()).add(entity);
            } else {
                layers.get(entity.getLayerName()).get(entity.getType()).add(entity);
            }
        }
    }
}
