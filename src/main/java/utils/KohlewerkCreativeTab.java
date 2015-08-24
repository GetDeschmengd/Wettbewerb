package utils;

import com.youtube.KohleWerk;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class KohlewerkCreativeTab extends CreativeTabs{

	public KohlewerkCreativeTab() {
		super("kohlewerk");
		
	}

	@Override
	public Item getTabIconItem() {
		
		return Item.getItemFromBlock(KohleWerk.blockmaschc);
	}

}
