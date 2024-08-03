package net.baffledbanana87.repurposedend.entity.client;

import com.google.common.collect.Sets;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.util.Set;

public class ModModelLayers {

    private static final Set<EntityModelLayer> LAYERS = Sets.<EntityModelLayer>newHashSet();

    public static final EntityModelLayer CRYING_SKELETON = registerMain("cryingskeleton");
    public static final EntityModelLayer CRYING_SKELETON_INNER_ARMOR = createInnerArmor("cryingskeleton");
    public static final EntityModelLayer CRYING_SKELETON_OUTER_ARMOR = createOuterArmor("cryingskeleton");



    private static EntityModelLayer registerMain(String id) {
        return register(id, "main");
    }

    private static EntityModelLayer register(String id, String layer) {
        EntityModelLayer entityModelLayer = create(id, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        } else {
            return entityModelLayer;
        }
    }

    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(Identifier.ofVanilla(id), layer);
    }

    private static EntityModelLayer createInnerArmor(String id) {
        return register("cryingskeleton", "inner_armor");
    }

    private static EntityModelLayer createOuterArmor(String id) {
        return register(id, "outer_armor");
    }

}
