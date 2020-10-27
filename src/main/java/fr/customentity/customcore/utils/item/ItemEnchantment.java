package fr.customentity.customcore.utils.item;

import com.google.common.base.Preconditions;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;


public class ItemEnchantment {

  private Enchantment enchantment;
  private int level;

  /**
   * Constructs a new {@link ItemEnchantment} with an {@link Enchantment} at level 1.
   *
   * @param enchantment enchantment
   */
  public ItemEnchantment( Enchantment enchantment) {
    this(enchantment, 1);
  }

  /**
   * Constructs a new {@link ItemEnchantment} with an {@link Enchantment} and a level.
   *
   * @param enchantment enchantment
   * @param level level of the enchantment
   */
  public ItemEnchantment(Enchantment enchantment, int level) {
    Preconditions.checkNotNull(enchantment, "enchantment cannot be null.");
    Preconditions.checkArgument(level > 0, "level cannot be less than 1");
    this.enchantment = enchantment;
    this.level = level;
  }

  /**
   * Applies this {@link ItemEnchantment} to an {@link ItemStack} safely.
   *
   * @param item item to apply this enchantment to
   *
   * @see ItemStack#addEnchantment(Enchantment, int)
   */
  public void apply( ItemStack item) {
    Preconditions.checkNotNull(item, "item cannot be null.");
    item.addEnchantment(this.enchantment, this.level);
  }

  /**
   * Applies this {@link ItemEnchantment} to an {@link ItemStack} unsafely, meaning it will
   * overwrite an existing enchantment of this type, if it exists.
   *
   * @param item item to apply this enchantment to
   *
   * @see ItemStack#addUnsafeEnchantment(Enchantment, int)
   */
  public void applyUnsafe( ItemStack item) {
    Preconditions.checkNotNull(item, "item cannot be null.");
    item.addUnsafeEnchantment(this.enchantment, this.level);
  }

  public Enchantment getEnchantment() {
    return enchantment;
  }

  public int getLevel() {
    return level;
  }
}






