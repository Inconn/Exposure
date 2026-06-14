package io.github.mortuusars.exposure.client.render.model;

import net.minecraft.client.renderer.block.model.BakedOverrides;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows combining models into one. Nicely used to add attachments to camera model dynamically, without making an override spaghetti.
 */
public class CompositeModel implements BakedModel {
    private final List<BakedModel> models;

    public CompositeModel(List<BakedModel> models) {
        this.models = models;
    }

    public CompositeModel(BakedModel... models) {
        this.models = List.of(models);
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, RandomSource random) {
        ArrayList<BakedQuad> list = new ArrayList<>();
        for (BakedModel model : models) {
            list.addAll(model.getQuads(state, direction, random));
        }
        return list;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return models.getFirst().useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return models.getFirst().isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return models.getFirst().usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return models.getFirst().isCustomRenderer();
    }

    @Override
    public @NotNull TextureAtlasSprite getParticleIcon() {
        return models.getFirst().getParticleIcon();
    }

    @Override
    public @NotNull ItemTransforms getTransforms() {
        return models.getFirst().getTransforms();
    }

    @Override
    public @NotNull BakedOverrides overrides() {
        return models.getFirst().overrides();
    }
}
