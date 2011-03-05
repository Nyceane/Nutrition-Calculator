package com.paschar.nutrition;

import java.util.ArrayList;

import com.paschar.nutrition.R;

public class FoodObject {
	public static final int FOODTYPE_GRAINS = 1;
	public static final int FOODTYPE_FRUITS = 2;
	public static final int FOODTYPE_VEGETABLES = 3;
	public static final int FOODTYPE_DIARY = 4;
	public static final int FOODTYPE_MEAT = 5;
	public static final int FOODTYPE_EXTRAS = 6;
	
	private int _foodId;
	private int _typeId;
	private int _drawableId;
	private int _servings;
	
	public FoodObject(int foodId, int typeId, int drawableId)
	{
		_foodId = foodId;
		_typeId = typeId;
		_drawableId = drawableId;
		_servings = 1;
	}
	
	public int GetFoodId()
	{
		return _foodId;
	}
	
	public int GetTypeId()
	{
		return _typeId;
	}
	
	public int GetDrawableId()
	{
		return _drawableId;
	}
	
	public int GetServings()
	{
		return _servings;
	}
	
	public void AddServing()
	{
		_servings = _servings + 1;
	}
	
	public static ArrayList<FoodObject> getGrains()
	{
		ArrayList<FoodObject> ret = new ArrayList<FoodObject>();
		
		ret.add(new FoodObject(51101000, FOODTYPE_GRAINS, R.drawable.whitebread));
		ret.add(new FoodObject(51155000, FOODTYPE_GRAINS, R.drawable.kaiser));
		ret.add(new FoodObject(51302010, FOODTYPE_GRAINS, R.drawable.wheatbread));
		ret.add(new FoodObject(54301000, FOODTYPE_GRAINS, R.drawable.crackers));
		ret.add(new FoodObject(54403000, FOODTYPE_GRAINS, R.drawable.popcorn));
		ret.add(new FoodObject(56203020, FOODTYPE_GRAINS, R.drawable.oatmeal));
		ret.add(new FoodObject(56205010, FOODTYPE_GRAINS, R.drawable.whiterice));
		ret.add(new FoodObject(56205310, FOODTYPE_GRAINS, R.drawable.brownrice));
		ret.add(new FoodObject(56207200, FOODTYPE_GRAINS, R.drawable.wheatcereal));
		ret.add(new FoodObject(57134000, FOODTYPE_GRAINS, R.drawable.cornflakes));
		return ret;
	}
	
	public static ArrayList<FoodObject> getFruits()
	{
		ArrayList<FoodObject> ret = new ArrayList<FoodObject>();
		
		ret.add(new FoodObject(61101010, FOODTYPE_FRUITS, R.drawable.grapefruit));
		ret.add(new FoodObject(61119010, FOODTYPE_FRUITS, R.drawable.orange));
		ret.add(new FoodObject(61210000, FOODTYPE_FRUITS, R.drawable.orangejuice));
		ret.add(new FoodObject(63101000, FOODTYPE_FRUITS, R.drawable.apple));
		ret.add(new FoodObject(63107010, FOODTYPE_FRUITS, R.drawable.banana));
		ret.add(new FoodObject(63123000, FOODTYPE_FRUITS, R.drawable.grapes));
		ret.add(new FoodObject(63126500, FOODTYPE_FRUITS, R.drawable.kiwi));
		ret.add(new FoodObject(63129010, FOODTYPE_FRUITS, R.drawable.mango));
		ret.add(new FoodObject(63135010, FOODTYPE_FRUITS, R.drawable.peach));
		ret.add(new FoodObject(63149010, FOODTYPE_FRUITS, R.drawable.watermelon));
		ret.add(new FoodObject(63223020, FOODTYPE_FRUITS, R.drawable.strawberry));
		ret.add(new FoodObject(64104010, FOODTYPE_FRUITS, R.drawable.applejuice));
		return ret;
	}
	
	public static ArrayList<FoodObject> getVegetables()
	{
		ArrayList<FoodObject> ret = new ArrayList<FoodObject>();
		
		ret.add(new FoodObject(71000100, FOODTYPE_VEGETABLES, R.drawable.potato));
		ret.add(new FoodObject(72125100, FOODTYPE_VEGETABLES, R.drawable.spinach));
		ret.add(new FoodObject(72201210, FOODTYPE_VEGETABLES, R.drawable.broccoli));
		ret.add(new FoodObject(73101010, FOODTYPE_VEGETABLES, R.drawable.carrot));
		ret.add(new FoodObject(74101000, FOODTYPE_VEGETABLES, R.drawable.tomateos));
		ret.add(new FoodObject(75109600, FOODTYPE_VEGETABLES, R.drawable.corn));
		ret.add(new FoodObject(75111000, FOODTYPE_VEGETABLES, R.drawable.cucumber));
		ret.add(new FoodObject(75111200, FOODTYPE_VEGETABLES, R.drawable.eggplant));
		ret.add(new FoodObject(75113000, FOODTYPE_VEGETABLES, R.drawable.lettuce));
		ret.add(new FoodObject(75115000, FOODTYPE_VEGETABLES, R.drawable.mushroom));
		ret.add(new FoodObject(75117020, FOODTYPE_VEGETABLES, R.drawable.onion));
		ret.add(new FoodObject(75125000, FOODTYPE_VEGETABLES, R.drawable.radish));
		ret.add(new FoodObject(76401010, FOODTYPE_VEGETABLES, R.drawable.greenbeans));
		return ret;
	}
	
	public static ArrayList<FoodObject> getDairy()
	{
		ArrayList<FoodObject> ret = new ArrayList<FoodObject>();

		ret.add(new FoodObject(11111000, FOODTYPE_DIARY, R.drawable.milk));
		ret.add(new FoodObject(11113000, FOODTYPE_DIARY, R.drawable.skim));
		ret.add(new FoodObject(11411200, FOODTYPE_DIARY, R.drawable.yogurt));
		ret.add(new FoodObject(11460420, FOODTYPE_DIARY, R.drawable.frozenyogurt));
		ret.add(new FoodObject(13220120, FOODTYPE_DIARY, R.drawable.pudding));
		ret.add(new FoodObject(14104010, FOODTYPE_DIARY, R.drawable.cheddar));
		ret.add(new FoodObject(14109010, FOODTYPE_DIARY, R.drawable.swiss));
		
		return ret;
	}
	
	public static ArrayList<FoodObject> getMeat()
	{
		ArrayList<FoodObject> ret = new ArrayList<FoodObject>();

		ret.add(new FoodObject(21101130, FOODTYPE_MEAT, R.drawable.leanbeef));
		ret.add(new FoodObject(22101120, FOODTYPE_MEAT, R.drawable.leanpork));
		ret.add(new FoodObject(22301120, FOODTYPE_MEAT, R.drawable.ham));
		ret.add(new FoodObject(24122120, FOODTYPE_MEAT, R.drawable.chicken));
		ret.add(new FoodObject(26137190, FOODTYPE_MEAT, R.drawable.salmon));
		ret.add(new FoodObject(26319110, FOODTYPE_MEAT, R.drawable.shrimp));
		ret.add(new FoodObject(31103000, FOODTYPE_MEAT, R.drawable.egg));
		ret.add(new FoodObject(31105000, FOODTYPE_MEAT, R.drawable.friedegg));		
		ret.add(new FoodObject(41210200, FOODTYPE_MEAT, R.drawable.blackbeans));
		ret.add(new FoodObject(42100100, FOODTYPE_MEAT, R.drawable.almonds));
		ret.add(new FoodObject(42104000, FOODTYPE_MEAT, R.drawable.cashews));
		
		return ret;
	}
	
	public static ArrayList<FoodObject> getExtras()
	{
		ArrayList<FoodObject> ret = new ArrayList<FoodObject>();

		ret.add(new FoodObject(92410310, FOODTYPE_EXTRAS, R.drawable.soda));
		ret.add(new FoodObject(13120720, FOODTYPE_EXTRAS, R.drawable.icecream));
		ret.add(new FoodObject(24134210, FOODTYPE_EXTRAS, R.drawable.friedchickenleg));
		ret.add(new FoodObject(25210280, FOODTYPE_EXTRAS, R.drawable.hotdog));
		ret.add(new FoodObject(27510480, FOODTYPE_EXTRAS, R.drawable.hamburger));
		ret.add(new FoodObject(53104500, FOODTYPE_EXTRAS, R.drawable.cakeslice));
		ret.add(new FoodObject(53206000, FOODTYPE_EXTRAS, R.drawable.cookie));
		ret.add(new FoodObject(53520140, FOODTYPE_EXTRAS, R.drawable.donut));
		ret.add(new FoodObject(58100120, FOODTYPE_EXTRAS, R.drawable.burrito));
		ret.add(new FoodObject(58101530, FOODTYPE_EXTRAS, R.drawable.taco));
		ret.add(new FoodObject(58106220, FOODTYPE_EXTRAS, R.drawable.pizzaslice));
		ret.add(new FoodObject(58407050, FOODTYPE_EXTRAS, R.drawable.ramen));
		ret.add(new FoodObject(71201010, FOODTYPE_EXTRAS, R.drawable.potatochips));
		ret.add(new FoodObject(71403000, FOODTYPE_EXTRAS, R.drawable.fries));
		ret.add(new FoodObject(91705010, FOODTYPE_EXTRAS, R.drawable.chocolate));
		ret.add(new FoodObject(91723000, FOODTYPE_EXTRAS, R.drawable.marshmallows));
		ret.add(new FoodObject(91726130, FOODTYPE_EXTRAS, R.drawable.milkywaybar));
		
		return ret;
	}
	
	public static ArrayList<FoodObject> getAllFood()
	{
		ArrayList<FoodObject> ret = new ArrayList<FoodObject>();
		
		ret.add(new FoodObject(51101000, 1, R.drawable.whitebread));
		ret.add(new FoodObject(51155000, 1, R.drawable.kaiser));
		ret.add(new FoodObject(51302010, 1, R.drawable.wheatbread));
		ret.add(new FoodObject(54301000, 1, R.drawable.crackers));
		ret.add(new FoodObject(54403000, 1, R.drawable.popcorn));
		ret.add(new FoodObject(56203020, 1, R.drawable.oatmeal));
		ret.add(new FoodObject(56205010, 1, R.drawable.whiterice));
		ret.add(new FoodObject(56205310, 1, R.drawable.brownrice));
		ret.add(new FoodObject(56207200, 1, R.drawable.wheatcereal));
		ret.add(new FoodObject(57134000, 1, R.drawable.cornflakes));
		
		ret.add(new FoodObject(61101010, 2, R.drawable.grapefruit));
		ret.add(new FoodObject(61119010, 2, R.drawable.orange));
		ret.add(new FoodObject(61210000, 2, R.drawable.orangejuice));
		ret.add(new FoodObject(63101000, 2, R.drawable.apple));
		ret.add(new FoodObject(63107010, 2, R.drawable.banana));
		ret.add(new FoodObject(63123000, 2, R.drawable.grapes));
		ret.add(new FoodObject(63126500, 2, R.drawable.kiwi));
		ret.add(new FoodObject(63129010, 2, R.drawable.mango));
		ret.add(new FoodObject(63135010, 2, R.drawable.peach));
		ret.add(new FoodObject(63149010, 2, R.drawable.watermelon));
		ret.add(new FoodObject(63223020, 2, R.drawable.strawberry));
		ret.add(new FoodObject(64104010, 2, R.drawable.applejuice));
		
		/*
		ret.add(new FoodObject(71000100, 3, R.drawable.potato));
		ret.add(new FoodObject(72125100, 3, R.drawable.spinach));
		ret.add(new FoodObject(72201210, 3, R.drawable.broccoli));
		ret.add(new FoodObject(73101010, 3, R.drawable.carrot));
		ret.add(new FoodObject(74101000, 3, R.drawable.tomateos));
		ret.add(new FoodObject(75109600, 3, R.drawable.corn));
		ret.add(new FoodObject(75111000, 3, R.drawable.cucumber));
		ret.add(new FoodObject(75111200, 3, R.drawable.eggplant));
		ret.add(new FoodObject(75113000, 3, R.drawable.lettuce));
		ret.add(new FoodObject(75115000, 3, R.drawable.mushroom));
		ret.add(new FoodObject(75117020, 3, R.drawable.onion));
		ret.add(new FoodObject(75125000, 3, R.drawable.radish));
		ret.add(new FoodObject(76401010, 3, R.drawable.greenbeans));
		
		
		ret.add(new FoodObject(11111000, 4, R.drawable.milk));
		ret.add(new FoodObject(11113000, 4, R.drawable.skim));
		ret.add(new FoodObject(11411200, 4, R.drawable.yogurt));
		ret.add(new FoodObject(11460420, 4, R.drawable.frozenyogurt));
		ret.add(new FoodObject(13220120, 4, R.drawable.pudding));
		ret.add(new FoodObject(14104010, 4, R.drawable.cheddar));
		ret.add(new FoodObject(14109010, 4, R.drawable.swiss));
		
		ret.add(new FoodObject(21101130, 5, R.drawable.leanbeef));
		ret.add(new FoodObject(22101120, 5, R.drawable.leanpork));
		ret.add(new FoodObject(22301120, 5, R.drawable.ham));
		ret.add(new FoodObject(24122120, 5, R.drawable.chicken));
		ret.add(new FoodObject(26137190, 5, R.drawable.salmon));
		ret.add(new FoodObject(26319110, 5, R.drawable.shrimp));
		ret.add(new FoodObject(31103000, 5, R.drawable.egg));
		ret.add(new FoodObject(31105000, 5, R.drawable.friedegg));		
		ret.add(new FoodObject(41210200, 5, R.drawable.blackbeans));
		ret.add(new FoodObject(42100100, 5, R.drawable.almonds));
		ret.add(new FoodObject(42104000, 5, R.drawable.cashews));
		
		ret.add(new FoodObject(92410310, 6, R.drawable.soda));
		ret.add(new FoodObject(13120720, 6, R.drawable.icecream));
		ret.add(new FoodObject(24134210, 6, R.drawable.friedchickenleg));
		ret.add(new FoodObject(25210280, 6, R.drawable.hotdog));
		ret.add(new FoodObject(27510480, 6, R.drawable.hamburger));
		ret.add(new FoodObject(53104500, 6, R.drawable.cakeslice));
		ret.add(new FoodObject(53206000, 6, R.drawable.cookie));
		ret.add(new FoodObject(53520140, 6, R.drawable.donut));
		ret.add(new FoodObject(58100120, 6, R.drawable.burrito));
		ret.add(new FoodObject(58101530, 6, R.drawable.taco));
		ret.add(new FoodObject(58106220, 6, R.drawable.pizzaslice));
		ret.add(new FoodObject(58407050, 6, R.drawable.ramen));
		ret.add(new FoodObject(71201010, 6, R.drawable.potatochips));
		ret.add(new FoodObject(71403000, 6, R.drawable.fries));
		ret.add(new FoodObject(91705010, 6, R.drawable.chocolate));
		ret.add(new FoodObject(91723000, 6, R.drawable.marshmallows));
		ret.add(new FoodObject(91726130, 6, R.drawable.milkywaybar));
		*/
		return ret;
	}
}
