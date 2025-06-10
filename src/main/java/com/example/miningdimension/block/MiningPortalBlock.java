package com.example.miningdimension.block;

import com.example.miningdimension.MiningDimensionMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;

public class MiningPortalBlock extends Block {
    public MiningPortalBlock(Properties props) {
        super(props);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isClientSide && player instanceof ServerPlayerEntity && player.getItemInHand(hand).isEmpty()) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            MinecraftServer server = serverPlayer.getServer();
            if (server != null) {
                ServerWorld target;
                if (world.dimension() == World.OVERWORLD) {
                    target = server.getLevel(World.NETHER);
                } else {
                    target = server.getLevel(World.OVERWORLD);
                }
                if (target != null) {
                    serverPlayer.teleportTo(target, 0.5, 64, 0.5, player.yRot, player.xRot);
                }
            }
            return ActionResultType.SUCCESS;
        }
        return super.use(state, world, pos, player, hand, hit);
    }
}
