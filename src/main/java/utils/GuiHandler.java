package utils;



import com.youtube.blocks.Tilenentitys.TileEntityMaschC;

import com.youtube.guis.ContainerMaschC;

import com.youtube.guis.GuiMaschC;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch(ID){
		
		case 0: return new ContainerMaschC(player.inventory, (IInventory)world.getTileEntity(new BlockPos(x, y, z)));
		
		default:  return null;
		}
		
		
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch(ID){
		
		case 0: return new GuiMaschC(player.inventory, (IInventory)world.getTileEntity(new BlockPos(x, y, z)), (TileEntityMaschC)world.getTileEntity(new BlockPos(x, y, z)));
		default: return null;
		}
		
	}

}
