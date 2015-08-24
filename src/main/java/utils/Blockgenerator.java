package utils;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class Blockgenerator implements IWorldGenerator{

	private Block block;
	
	public Blockgenerator(Block block){
		this.block = block;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		if(world.provider.getDimensionId() == 0){
			chunkX *= 16;
			chunkZ *= 16;
			for(int i = 0; i < 25; i++){

				int posX = chunkX + random.nextInt(16);
				int posZ = chunkZ + random.nextInt(16);
				int posY = random.nextInt(100);
				WorldGenMinable wgn = new WorldGenMinable(block.getDefaultState(), 1);
				wgn.generate(world, random, new BlockPos(posX, posY, posZ));
			}
		}
		
	}
	
	
}
