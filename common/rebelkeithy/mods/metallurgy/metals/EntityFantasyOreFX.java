package rebelkeithy.mods.metallurgy.metals;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class EntityFantasyOreFX extends EntityFX
{
    private final float scaleFlameFX;

    public EntityFantasyOreFX(final World world, final double particleRed,
            final double particleGreen, final double particleBlue, final double baseMotionX,
            final double baseMotionY, final double baseMotionZ)
    {
        super(world, particleRed, particleGreen, particleBlue, baseMotionX, baseMotionY,
                baseMotionZ);
        motionX = motionX * 0.01D;
        motionY = motionY * 0.01D;
        motionZ = motionZ * 0.01D;

        scaleFlameFX = particleScale * 0.8f;
        particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
        noClip = true;
        setParticleTextureIndex(Math.random() > 0.5f ? 1 : 0);
    }

    private float ageParticle(final float variance)
    {
        float portionLife = (particleAge + variance) / particleMaxAge;

        if (portionLife < 0.0F) portionLife = 0.0F;

        if (portionLife > 1.0F) portionLife = 0.5F;
        portionLife = portionLife / 2;
        return portionLife;
    }

    @Override
    public float getBrightness(final float variance)
    {
        final float particleAge = ageParticle(variance);
        return super.getBrightness(variance) * particleAge + (1.0F - particleAge);
    }

    @Override
    public int getBrightnessForRender(final float variance)
    {
        final float particleAge = ageParticle(variance);

        final int brightness = super.getBrightnessForRender(variance);
        int brightnessLSB = brightness & 255;
        final int brightnessHSB = brightness >> 16 & 255;
        brightnessLSB += (int) (particleAge * 15.0F * 16.0F);

        if (brightnessLSB > 240) brightnessLSB = 240;

        return brightnessLSB | brightnessHSB << 16;
    }

    @Override
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        if (particleAge++ >= particleMaxAge) setDead();

        if (particleAge == particleMaxAge / 2) setParticleTextureIndex(1);

        moveEntity(motionX, motionY, motionZ);
        if (posY == prevPosY)
        {
            motionX *= 1.1D;
            motionZ *= 1.1D;
        }

        motionX *= 0.96D;
        motionY *= 0.96D;
        motionZ *= 0.96D;

        if (onGround)
        {
            motionX *= 0.7D;
            motionZ *= 0.7D;
        }
    }

    @Override
    public void renderParticle(final Tessellator tessellator, final float f, final float f1,
            final float f2, final float f3, final float f4, final float f5)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        final Tessellator tessellator1 = new Tessellator();
        tessellator1.startDrawingQuads();
        tessellator1.setBrightness(getBrightnessForRender(f));

        float f6 = (particleAge + f) / particleMaxAge * 32F;
        if (f6 < 0.0F) f6 = 0.0F;
        if (f6 > 1.0F) f6 = 1.0F;

        final float var8 = (particleAge + f) / particleMaxAge;
        particleScale = scaleFlameFX * (1.0F - var8 * var8 * 0.5F);

        final ResourceLocation texture =
                new ResourceLocation("Metallurgy:textures/particles/FantasyMetalsParticle.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        final float f0 = 0;
        final float f7 = f0 + 1 / 16F;
        final float f8 = 1;
        final float f9 = f8 + 1 / 16F;
        final float f10 = 0.1F * particleScale;
        final float f11 = (float) (prevPosX + (posX - prevPosX) * f - interpPosX);
        final float f12 = (float) (prevPosY + (posY - prevPosY) * f - interpPosY);
        final float f13 = (float) (prevPosZ + (posZ - prevPosZ) * f - interpPosZ);

        tessellator1.setColorRGBA_F(particleRed, particleGreen, particleBlue, 0.9f);
        tessellator1.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5
                * f10, f7, f9);
        tessellator1.addVertexWithUV(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5
                * f10, f7, f8);
        tessellator1.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5
                * f10, f0, f8);
        tessellator1.addVertexWithUV(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5
                * f10, f0, f9);

        tessellator1.draw();

        final ResourceLocation defaultParticles =
                new ResourceLocation("textures/particle/particles.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(defaultParticles);
    }

}
