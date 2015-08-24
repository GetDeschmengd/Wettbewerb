package utils;

import java.util.Iterator;
import java.util.Map;





import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.youtube.KohleWerk;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Recipies {

    private static final Recipies kohlebasis = new Recipies();
  
    private final Map recipieList = Maps.newHashMap();


    public static Recipies instance()
    {
        return kohlebasis;
    }

    private Recipies()
    {
        addRecipie(new ItemStack((Items.coal)),  new ItemStack(KohleWerk.koks));
        addRecipie(new ItemStack(Blocks.wool), new ItemStack(Blocks.web));
        addRecipie(new ItemStack(Blocks.dirt), new ItemStack(Blocks.gravel));
        addRecipie(new ItemStack(Blocks.diamond_ore), new ItemStack(Items.diamond, 2, 0));
    }

    public void addRecipie(ItemStack parItemStackIn,  ItemStack parItemStackOut)
    {
        recipieList.put(parItemStackIn, parItemStackOut);

    }
        
  
    public ItemStack getResult(ItemStack parItemStack)
    {
        Iterator iterator = recipieList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!areItemStacksEqual(parItemStack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean areItemStacksEqual(ItemStack parItemStack1,  ItemStack parItemStack2)
    {
        return parItemStack2.getItem() == parItemStack1.getItem() 

              && (parItemStack2.getMetadata() == 32767 

              || parItemStack2.getMetadata() == parItemStack1

              .getMetadata());
    }

    public Map getRecipieList()
    {
        return recipieList;
    }

    
}
