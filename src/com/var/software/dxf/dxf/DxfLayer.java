package com.var.software.dxf.dxf;

import com.var.software.dxf.dxf.entities.DxfEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Dxf document struct object.
 */
public class DxfLayer {

    /*Resolved dxf objects, split with layer.*/
    private Map<String, Map<String, List<DxfEntity>>> layers;

    /**
     * Init object(s).
     */
    public DxfLayer() {
        layers = new HashMap<>();
    }

    /**
     * Get all layer.
     *
     * @return return Map<String,Map<String,List<DxfEntity>>> object.
     */
    public Map<String, Map<String, List<DxfEntity>>> getLayers() {
        return layers;
    }

    /**
     * Get dxf layer with layerName.
     *
     * @param key layerName.
     * @return if find, return Map<String,List<DxfEntity>> object, if not find, return null.
     */
    public Map<String, List<DxfEntity>> getLayer(String key) {
        return layers.get(key);
    }

    /**
     * Add DxfEntity to layer.
     *
     * @param entity DxfEntity or extends DxfEntity object.
     */
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
