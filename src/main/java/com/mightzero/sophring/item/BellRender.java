package com.mightzero.sophring.item;

import com.mightzero.sophring.Sophring;
import com.mightzero.sophring.item.Bell;
import com.mightzero.sophring.item.BellModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import static net.minecraft.client.gui.tooltip.BundleTooltipComponent.TEXTURE;
public class BellRender <T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private final BellModel model;

    public BellRender(FeatureRendererContext<T, M> context) {
        super(context);
        TexturedModelData data = BellModel.getTexturedModelData();
        ModelPart root = data.createModel();
        this.model = new BellModel(root);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack boots = entity.getEquippedStack(EquipmentSlot.FEET);
        if (boots.getItem() == Sophring.Bell) {
            Identifier texture;
            if(boots.getDamage()<100) texture = new Identifier("sophring", "textures/entity/bell.png");
            else texture = new Identifier("sophring", "textures/entity/bell_broken.png");
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(texture));
            this.model.setAngles(entity, limbAngle, limbDistance, tickDelta, headYaw, headPitch);
            this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
