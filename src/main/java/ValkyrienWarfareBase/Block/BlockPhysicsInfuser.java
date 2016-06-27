package ValkyrienWarfareBase.Block;

import ValkyrienWarfareBase.PhysicsManagement.PhysicsWrapperEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPhysicsInfuser extends Block {

	public BlockPhysicsInfuser(Material materialIn) {
		super(materialIn);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(!worldIn.isRemote){
			PhysicsWrapperEntity wrapper = new PhysicsWrapperEntity(worldIn,pos.getX(),pos.getY(),pos.getZ());
        	worldIn.spawnEntityInWorld(wrapper);
		}
		return true;
    }

}