package com.youtube.blocks;

import com.youtube.KohleWerk;


import com.youtube.blocks.Tilenentitys.TileEntityMaschC;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockMaschC extends BlockContainer{

	public BlockMaschC() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("maschc");
        setCreativeTab(CreativeTabs.tabBlock);
        
        blockParticleGravity = 1.0F;
        slipperiness = 0.6F;
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        lightOpacity = 20; // cast a light shadow
        setTickRandomly(false);
        useNeighborBrightness = false;
	}
	
	
	
	   @Override
	    public boolean onBlockActivated(
	          World parWorld, 
	          BlockPos parBlockPos, 
	          IBlockState parIBlockState, 
	          EntityPlayer parPlayer, 
	          EnumFacing parSide, 
	          float hitX, 
	          float hitY, 
	          float hitZ)
	    {
	        if (!parWorld.isRemote)
	        {
	            parPlayer.openGui(KohleWerk.instance, 
	                  0, 
	                  parWorld, 
	                  parBlockPos.getX(), 
	                  parBlockPos.getY(), 
	                  parBlockPos.getZ()); 
	        }
	        
	        return true;
	    }

	
	@Override
	public int getRenderType() {
		
		return 3;
	}


   



	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return (TileEntity) new TileEntityMaschC();
	}



	public static void blockverändern(boolean etwastransformieren, World worldObj, BlockPos pos) {
		
		
	}
	
}
