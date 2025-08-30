package arikoth.world.type;

import arc.struct.Seq;
import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;
import mindustry.gen.Building;
import mindustry.type.*;

//author @Lao_Hua-Ji

public class Recipe{
    public static Recipe empty = new Recipe();
    public Seq<ItemStack> inputItem = new Seq<>();
    public Seq<LiquidStack> inputLiquid = new Seq<>();
    public Seq<PayloadStack> inputPayload = new Seq<>();

    public Seq<ItemStack> outputItem = new Seq<>();
    public Seq<LiquidStack> outputLiquid = new Seq<>();
    public Seq<PayloadStack> outputPayload = new Seq<>();

    public float craftTime = 60f;

    public Recipe(Object...objects){
        for (int i = 0; i < objects.length / 2; i++){
            if (objects[i * 2] instanceof Item item && objects[i * 2 + 1] instanceof Integer count){
                inputItem.add(new ItemStack(item, count));
            }else if (objects[i * 2] instanceof Liquid liquid && objects[i * 2 + 1] instanceof Float count){
                inputLiquid.add(new LiquidStack(liquid, count));
            }else if (objects[i * 2] instanceof UnlockableContent payload && objects[i * 2 + 1] instanceof Integer count){
                inputPayload.add(new PayloadStack(payload, count));
            }
        }
    }
}
