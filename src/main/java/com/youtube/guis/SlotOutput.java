package com.youtube.guis;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOutput extends Slot
{

	public SlotOutput(EntityPlayer player, IInventory inventoryIn, int index, int xPosition,
			int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	
	}

	
		@Override
		public boolean isItemValid(ItemStack stack) {
	
		return false;
		}
	

	
	}


