package com.youtube.blocks.Tilenentitys;

import utils.Recipies;

import com.youtube.KohleWerk;
import com.youtube.blocks.BlockMaschC;
import com.youtube.guis.ContainerMaschC;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class TileEntityMaschC extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory{

	public static boolean isoutput = false;
	public static boolean iscoalinside = false;
	public static boolean isfertig = false;
	
    public enum slotEnum 
    {
        INPUT_SLOT, OUTPUT_SLOT
    }
    private static final int[] slotsOben = new int[] {slotEnum.INPUT_SLOT.ordinal()};
    private static final int[] slotsUnten = new int[]{slotEnum.OUTPUT_SLOT.ordinal()};
    private static final int[] slotsSeite = new int[] {};
    private ItemStack[] kohleItemStackl�nge = new ItemStack[2];
    private int zeitvoneinemitem; 

    private int geradeBrennZeit;
    private int Zeitbisjezt;
    private int zeitproitem;
    private String name = "Kohletransformierer";
	   
	
	public static int Zeit = 0;
	
	@Override
	public int getSizeInventory() {
		
		return kohleItemStackl�nge.length;
	}

	@Override
	public ItemStack getStackInSlot(int index){
		
		return kohleItemStackl�nge[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int zahl){
		if (kohleItemStackl�nge[index] != null){
            ItemStack itemstack;

            if (kohleItemStackl�nge[index].stackSize <= zahl){
                itemstack = kohleItemStackl�nge[index];
                kohleItemStackl�nge[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = kohleItemStackl�nge[index].splitStack(zahl);

                if (kohleItemStackl�nge[index].stackSize == 0){
                    kohleItemStackl�nge[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	

	@Override
	public ItemStack getStackInSlotOnClosing(int index){
		
		 if (kohleItemStackl�nge[index] != null){
	            ItemStack itemstack = kohleItemStackl�nge[index];
	            kohleItemStackl�nge[index] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack){
		boolean selberStack = stack != null  && stack.isItemEqual(kohleItemStackl�nge[index]) && ItemStack.areItemStackTagsEqual(stack,  kohleItemStackl�nge[index]);
	        kohleItemStackl�nge[index] = stack;

	        if (stack != null && stack.stackSize > getInventoryStackLimit()){
	            stack.stackSize = getInventoryStackLimit();
	        }

	       
	        if (index == slotEnum.INPUT_SLOT.ordinal()  && !selberStack){
	            zeitproitem = Zeitzumverarbeitenvomitem(stack);
	            Zeitbisjezt = 0;
	            markDirty();
	        }
		
	}

	public int Zeitzumverarbeitenvomitem(ItemStack item){
		return 54;
	}
	
	
	
	@Override
	public int getInventoryStackLimit() {
		
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(pos) != this ? false : 

            player.getDistanceSq(pos.getX()+0.5D, pos.getY()+0.5D, 

            pos.getZ()+0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
			
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		 return index == slotEnum.INPUT_SLOT.ordinal() ? true : false;
	}

	@Override
	public  int getField(int id) {
		switch (id)
        {
            case 0:
                return zeitproitem;
            case 1:
                return geradeBrennZeit;
            case 2:
                return Zeitbisjezt;
            case 3:
                return zeitvoneinemitem;
            default:
                return 0;
        }
	}

	@Override
	public void setField(int id, int value) {
		switch (id)
        {
            case 0:
               zeitproitem = value;
                break;
            case 1:
                geradeBrennZeit = value;
                break;
            case 2:
                Zeitbisjezt = value;
                break;
            case 3:
                zeitvoneinemitem = value;
                break;
        default:
            break;
        }
		
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		for (int i = 0; i < kohleItemStackl�nge.length; ++i){
            kohleItemStackl�nge[i] = null;
        }
    }
		
	

	@Override
	public String getName() {
		 return  name;
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory,
			EntityPlayer playerIn) {
		
		return new ContainerMaschC(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "0";
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? slotsUnten : 

            (side == EnumFacing.UP ? slotsOben : slotsSeite);
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn,
			EnumFacing direction) {
		 return isItemValidForSlot(index, itemStackIn);
		
	}
  
	

	@Override
	public void update() {

		    Zeit = zeitvoneinemitem;
		
	  
	        boolean schongemacht = maschtgrad();
	        boolean status�ndern = false;

	        
	        if(zeitvoneinemitem == 0){
	        	isfertig = true;
	        }
	        
	        
	         if(kohleItemStackl�nge[slotEnum.OUTPUT_SLOT.ordinal()] != null){
	        
	        	 isoutput  = true;
	        	 
	         }	        
	        iscoalinside = KannMachen();
	        
	        if (maschtgrad())
	        {
	            --zeitvoneinemitem;
	        }

	        if (!worldObj.isRemote)
	        {
	            
	            if (kohleItemStackl�nge[slotEnum.INPUT_SLOT.ordinal()] != null){                
	                 
	                if (!maschtgrad() && KannMachen())
	                {

	                     zeitvoneinemitem = 150;

	    
	                     if (maschtgrad())
	                     {
	                        status�ndern  = true;
	                     }
	                }

	                
	                if (maschtgrad() && KannMachen())
	                {
	                    ++Zeitbisjezt;
	                    
	                    
	                    if (Zeitbisjezt == zeitproitem)
	                    {
	                        Zeitbisjezt = 0;
	                        zeitproitem = Zeitzumverarbeitenvomitem( kohleItemStackl�nge[0]);
	                        machen();
	                        status�ndern= true;
	                    }
	                }
	                else
	                {
	                   Zeitbisjezt = 0;
	                }
	            }

	            
	            if (schongemacht != maschtgrad())
	            {
	                

	            }
	        }

	        if (status�ndern)
	        {
	            markDirty();
	        }
		
	}
	
	

    private boolean KannMachen()
    {
      
    	
        if (kohleItemStackl�nge[slotEnum.INPUT_SLOT.ordinal()] == null)
        {
            return false;
        }
        else 
        {
            ItemStack itemStackToOutput = Recipies.instance().getResult(kohleItemStackl�nge[slotEnum.INPUT_SLOT.ordinal()]);
            if(itemStackToOutput != null){
            	
            	iscoalinside = true;
            	
            }
            if (itemStackToOutput == null) return false; 
            if (kohleItemStackl�nge[slotEnum.OUTPUT_SLOT.ordinal()] == null) return true; 
            if (!kohleItemStackl�nge[slotEnum.OUTPUT_SLOT.ordinal()].isItemEqual(itemStackToOutput)) return false; 
            int result = kohleItemStackl�nge[slotEnum.OUTPUT_SLOT.ordinal()].stackSize + itemStackToOutput.stackSize;
            return result <= getInventoryStackLimit() && result <= kohleItemStackl�nge[slotEnum.OUTPUT_SLOT.ordinal()].getMaxStackSize();
        }
    }

	
    @Override

    public boolean shouldRefresh(World parWorld, BlockPos parPos, 

          IBlockState parOldState, IBlockState parNewState)
    {
        return false;
    }

	@Override
	public boolean canExtractItem(int index, ItemStack stack,
			EnumFacing direction) {
		
		return true;
	}

	public void machen()
    {
        if (KannMachen())
        {
            ItemStack itemstack = Recipies.instance() .getResult(kohleItemStackl�nge[slotEnum.INPUT_SLOT.ordinal()]);

           
            if (kohleItemStackl�nge[slotEnum.OUTPUT_SLOT.ordinal()] == null)
            {
                kohleItemStackl�nge[slotEnum.OUTPUT_SLOT.ordinal()] = itemstack.copy();

            }
            else if (kohleItemStackl�nge[slotEnum.OUTPUT_SLOT.ordinal()].getItem() == itemstack.getItem())
            {
                kohleItemStackl�nge[slotEnum.OUTPUT_SLOT.ordinal()].stackSize += itemstack.stackSize; 
            }

            --kohleItemStackl�nge[slotEnum.INPUT_SLOT.ordinal()].stackSize;

            if (kohleItemStackl�nge[slotEnum.INPUT_SLOT.ordinal()]

                  .stackSize <= 0)
            {
                kohleItemStackl�nge[slotEnum.INPUT_SLOT.ordinal()] = null;
            }
        }
    }
	
	@Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        kohleItemStackl�nge = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbtTagCompound = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbtTagCompound.getByte("Slot");

            if (b0 >= 0 && b0 < kohleItemStackl�nge.length)
            {
                kohleItemStackl�nge[b0] = ItemStack.loadItemStackFromNBT(

                      nbtTagCompound);
            }
        }

        zeitvoneinemitem = compound.getShort("GrindTime");
        Zeitbisjezt = compound.getShort("CookTime");
        zeitproitem= compound.getShort("CookTimeTotal");

        if (compound.hasKey("CustomName", 8))
        {
            name = compound.getString("CustomName");
        }
    }


    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("GrindTime", (short)zeitvoneinemitem);
        compound.setShort("CookTime", (short)Zeitbisjezt);
        compound.setShort("CookTimeTotal", (short)zeitproitem);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < kohleItemStackl�nge.length; ++i)
        {
            if (kohleItemStackl�nge[i] != null)
            {
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setByte("Slot", (byte)i);
                kohleItemStackl�nge[i].writeToNBT(nbtTagCompound);
                nbttaglist.appendTag(nbtTagCompound);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (hasCustomName())
        {
            compound.setString("CustomName", name);
        }
    }

    public boolean maschtgrad()
    {
        return true;
    }

   
   
    public static boolean containerzeichnen(IInventory parIInventory)
    {
        return true;
    }
   
}
