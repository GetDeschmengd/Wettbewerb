package com.youtube.blocks;

import java.util.Random;

import com.youtube.KohleWerk;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockOreMaschC extends Block{

	public BlockOreMaschC() {
		super(Material.rock);
		
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("maschcore");
		
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if(!worldIn.isRemote){
			
			
			
		}
	}
	
}
