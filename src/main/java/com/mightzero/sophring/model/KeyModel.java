package com.mightzero.sophring.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector3f;

public class KeyModel extends EntityModel<LivingEntity> {
	private final ModelPart main;
	public KeyModel(ModelPart root) {
		this.main = root.getChild("main");
	}
	public static TexturedModelData getTexturedModelData() {
		final float offsetY=12.0F;
		final float offsetZ=-12.0F;
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild("main",ModelPartBuilder.create()
				.uv(0, 0).cuboid(-1.0F, -3.0F+offsetY, -1.0F+offsetZ, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-5.0F, 6.0F+offsetY, -1.0F+offsetZ, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-4.0F, 5.0F+offsetY, -1.0F+offsetZ, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-5.0F, 2.0F+offsetY, -1.0F+offsetZ, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-4.0F, 2.0F+offsetY, -1.0F+offsetZ, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-3.0F, 3.0F+offsetY, -1.0F+offsetZ, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.0F, 4.0F+offsetY, -1.0F+offsetZ, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(3.0F, 6.0F+offsetY, -1.0F+offsetZ, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(3.0F, 2.0F+offsetY, -1.0F+offsetZ, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(2.0F, 5.0F+offsetY, -1.0F+offsetZ, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(2.0F, 2.0F+offsetY, -1.0F+offsetZ, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(1.0F, 3.0F+offsetY, -1.0F+offsetZ, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-1.0F, -7.0F+offsetY, -1.0F+offsetZ, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-1.0F, 1.0F+offsetY, -1.0F+offsetZ, 1.0F, 7.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.0F, 0.0F+offsetY, -1.0F+offsetZ, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-3.0F, -1.0F+offsetY, -1.0F+offsetZ, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-3.0F, -5.0F+offsetY, -1.0F+offsetZ, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-4.0F, -4.0F+offsetY, -1.0F+offsetZ, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(1.0F, -5.0F+offsetY, -1.0F+offsetZ, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(1.0F, -1.0F+offsetY, -1.0F+offsetZ, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.0F, -6.0F+offsetY, -1.0F+offsetZ, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(2.0F, -4.0F+offsetY, -1.0F+offsetZ, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//this.main.scale(new Vector3f(2.0F,2.0F,2.0F));
		this.main.xScale=0.2F;
		this.main.yScale=0.2F;
		this.main.zScale=0.2F;
		this.main.pitch = 0.0F;
		float f = entity.handSwingProgress;
		this.main.yaw = MathHelper.sin(MathHelper.sqrt(f) * 6.2831855F) * 0.2F;
		if (entity.isInSneakingPose()) {
			this.main.pitch = 0.5F;
			this.main.pivotY = 3.2F;
		}
		else {
			this.main.pitch = 0.0F;
			this.main.pivotY = 0.0F;
		}
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}