// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

package com.mightzero.sophring.item;

import com.mightzero.sophring.Sophring;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.model.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class BellModel extends EntityModel<LivingEntity> {
	private final ModelPart main;
	public BellModel(ModelPart root) {
		this.main = root.getChild("main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		final float XOffset=2.5F;
		final float YOffset=-2.0F;
		final float ZOffset=1.0F;
		final float DilateR=-1.0F;
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create()/*.uv(12, 4).cuboid(-4.25F+XOffset, -1.0F+YOffset, 3.0F+ZOffset, 8.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 10).cuboid(3.75F+XOffset, -1.0F+YOffset, -5.0F+ZOffset, 2.0F, 2.0F, 8.0F, new Dilation(0.0F))
				.uv(12, 0).cuboid(-4.25F+XOffset, -1.0F+YOffset, -7.0F+ZOffset, 8.0F, 2.0F, 2.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-6.25F+XOffset, -1.0F+YOffset, -5.0F+ZOffset, 2.0F, 2.0F, 8.0F, new Dilation(0.0F))*/
				.uv(0, 0).cuboid(-8.25F+XOffset, 9.0F+YOffset, -2.0F+ZOffset, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if(entity.isSneaking())
		{
			this.main.pivotZ = 4.0F;
			this.main.pivotY = 12.2F;
		}
		else
		{
			this.main.pivotZ = 0.0F;
			this.main.pivotY = 12.0F;
		}
		this.main.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.main.yaw = 0;
		this.main.pitch = MathHelper.lerp(entity.getLeaningPitch(ageInTicks), this.main.pitch, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}