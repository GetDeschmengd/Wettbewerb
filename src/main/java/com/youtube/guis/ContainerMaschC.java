package com.youtube.guis;




import com.youtube.blocks.Tilenentitys.TileEntityMaschC;

import sun.nio.cs.ext.MacHebrew;
import utils.Recipies;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMaschC extends Container{

	 private final IInventory maschc;
	    private final int inventarGösse;
	    private int zeitbisjetzt;
	    private int zeitproitem;
	    private int zeitvonitem;
	
	    
	    

	    public ContainerMaschC(InventoryPlayer parInventoryPlayer, 
	          IInventory parIInventory)
	    {
	        
	        
	        maschc = parIInventory;
	        inventarGösse = maschc.getSizeInventory();
	        
	        addSlotToContainer(new Slot(maschc,  TileEntityMaschC.slotEnum.INPUT_SLOT.ordinal(), 9, 49));
	        
	        
	        
	        addSlotToContainer(new SlotOutput(parInventoryPlayer.player, maschc, TileEntityMaschC.slotEnum.OUTPUT_SLOT.ordinal(),  151, 49));
	        
	        
	        
	        
	        int i;
	        for (i = 0; i < 3; ++i)
	        {
	            for (int j = 0; j < 9; ++j)
	            {
	                addSlotToContainer(new Slot(parInventoryPlayer, j+i*9+9, 
	                8+j*18, 84+i*18));
	            }
	        }

	        for (i = 0; i < 9; ++i)
	        {
	            addSlotToContainer(new Slot(parInventoryPlayer, i, 8 + i * 18, 
	            142));
	        }
	    }
	        
	    

	    @Override
	    public void addCraftingToCrafters(ICrafting listener)
	    {
	        super.addCraftingToCrafters(listener);
	        listener.func_175173_a(this, maschc);
	    }
	    
	    @Override
	    public void detectAndSendChanges()
	    {
	        super.detectAndSendChanges();

	        for (int i = 0; i < crafters.size(); ++i)
	        {
	            ICrafting icrafting = (ICrafting)crafters.get(i);

	            if ( zeitbisjetzt != maschc.getField(2))
	            {
	                icrafting.sendProgressBarUpdate(this, 2, 
	                      maschc.getField(2));
	            }

	            if (zeitvonitem != maschc.getField(0))
	            {
	                icrafting.sendProgressBarUpdate(this, 0, 
	                      maschc.getField(0));
	            }

	            if (zeitproitem != maschc.getField(3))
	            {
	                icrafting.sendProgressBarUpdate(this, 3, 
	                      maschc.getField(3));
	            }
	        }

	        zeitbisjetzt = maschc.getField(2);
	        zeitvonitem = maschc.getField(0); 
	        zeitproitem = maschc.getField(3); 
	    }

	    @Override
	   
	    public void updateProgressBar(int id, int data)
	    {
	       maschc.setField(id, data);
	    }
	   	    
	    @Override
	    public boolean canInteractWith(EntityPlayer playerIn)
	    {
	        return maschc.isUseableByPlayer(playerIn);
	    }
	    
	    
	    @Override
	    public ItemStack transferStackInSlot(EntityPlayer playerIn, 
	          int slotIndex)
	    {
	        ItemStack itemStack1 = null;
	        Slot slot = (Slot)inventorySlots.get(slotIndex);

	        if (slot != null && slot.getHasStack())
	        {
	            ItemStack itemStack2 = slot.getStack();
	            itemStack1 = itemStack2.copy();

	            if (slotIndex == TileEntityMaschC.slotEnum.OUTPUT_SLOT
	                  .ordinal())
	            {
	                if (!mergeItemStack(itemStack2, inventarGösse, 
	                      inventarGösse+36, true))
	                {
	                    return null;
	                }

	                slot.onSlotChange(itemStack2, itemStack1);
	            }
	            else if (slotIndex != TileEntityMaschC.slotEnum.INPUT_SLOT
	                  .ordinal())
	            {
	                
	                if (Recipies.instance().getResult(itemStack2) 
	                      != null)
	                {
	                    if (!mergeItemStack(itemStack2, 0, 1, false))
	                    {
	                        return null;
	                    }
	                }
	                else if (slotIndex >= inventarGösse 
	                     && slotIndex < inventarGösse+27) // player inventory slots
	                {
	                    if (!mergeItemStack(itemStack2, inventarGösse+27, 
	                          inventarGösse+36, false))
	                    {
	                        return null;
	                    }
	                }
	                else if (slotIndex >= inventarGösse+27 
	                      && slotIndex < inventarGösse+36 
	                      && !mergeItemStack(itemStack2, inventarGösse+1, 
	                      inventarGösse+27, false)) // hotbar slots
	                {
	                    return null;
	                }
	            }
	            else if (!mergeItemStack(itemStack2, inventarGösse, 
	                  inventarGösse+36, false))
	            {
	                return null;
	            }

	            if (itemStack2.stackSize == 0)
	            {
	                slot.putStack((ItemStack)null);
	            }
	            else
	            {
	                slot.onSlotChanged();
	            }

	            if (itemStack2.stackSize == itemStack1.stackSize)
	            {
	                return null;
	            }

	            slot.onPickupFromSlot(playerIn, itemStack2);
	        }

	        return itemStack1;
	    }

	
	
}
