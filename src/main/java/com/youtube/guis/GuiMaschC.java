package com.youtube.guis;

import utils.Recipies;

import com.ibm.icu.impl.duration.TimeUnit;
import com.youtube.blocks.Tilenentitys.TileEntityMaschC;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiMaschC extends GuiContainer{
	
	private boolean b = false;
	int progress = 0;
	
	private static final ResourceLocation GuiTextures = new ResourceLocation("kohlewerk:textures/gui/guimaschc.png");
	    private final InventoryPlayer inventoryPlayer;
	    private final IInventory MaschC;
        private int zeitbisjetzt;
        private int zeitproitem;
	    
	    
	    
	    public GuiMaschC(InventoryPlayer parInventoryPlayer, IInventory sparInventoryMaschC, TileEntityMaschC maschc)
	    {
	        super(new ContainerMaschC(parInventoryPlayer,  sparInventoryMaschC));
	        inventoryPlayer = parInventoryPlayer;
	        MaschC = sparInventoryMaschC;
	    }

	    @Override

	    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	    {
	        String s = MaschC.getDisplayName().getUnformattedText();
	        fontRendererObj.drawString(s, xSize/2-fontRendererObj.getStringWidth(s)/2, 6, 4210752);

	        fontRendererObj.drawString(inventoryPlayer.getDisplayName().getUnformattedText(), 8, ySize - 96 + 2, 4210752);
	    }

	    
	    
		@Override
		protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
	              GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	              mc.getTextureManager().bindTexture(GuiTextures);
	              int marginHorizontal = (width - xSize) / 2;
	              int marginVertical = (height - ySize) / 2;
	              drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0, xSize, ySize);
	              
	              
	              int progressLevel = getProgressLevel(72);
	              
	              
	              if(TileEntityMaschC.iscoalinside){
	            	 
	            	  b = true;
	              }
	              
	             if(b){
	            
	            	 
	            	 
	            	 
	            	  progress++;
		              
	            	  
		              
		              drawTexturedModalRect(marginHorizontal + 8, marginVertical + 20, 0, 166, progress, 16);
		              
		              
		             
		            	       
		            
		              if(progress >= 160){
		            	  progress = 0;
		            	  b = false;
		              }
		              
		              
	             }   
		              
		}

		
		private int getProgressLevel(int progressbarlänge)
		    {
		         zeitbisjetzt = MaschC.getField(2); 
		         zeitproitem = MaschC.getField(3);
		        return zeitproitem != 0 && zeitbisjetzt != 0 ? zeitbisjetzt*progressbarlänge/zeitproitem: 0;

		    }
}
