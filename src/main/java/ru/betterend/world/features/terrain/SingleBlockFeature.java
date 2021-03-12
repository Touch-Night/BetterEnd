package ru.betterend.world.features.terrain;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import ru.betterend.registry.EndTags;
import ru.betterend.util.BlocksHelper;
import ru.betterend.world.features.DefaultFeature;

public class SingleBlockFeature extends DefaultFeature {
	private final Block block;
	
	public SingleBlockFeature(Block block) {
		this.block = block;
	}
	
	@Override
	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		if (!world.getBlockState(pos.down()).isIn(EndTags.GEN_TERRAIN)) {
			return false;
		}
		
		BlockState state = block.getDefaultState();
		if (block.getStateManager().getProperty("waterlogged") != null) {
			boolean waterlogged = !world.getFluidState(pos).isEmpty();
			state = state.with(Properties.WATERLOGGED, waterlogged);
		}
		BlocksHelper.setWithoutUpdate(world, pos, state);
		
		return true;
	}
}