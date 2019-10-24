package org.valkyrienskies.mod.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.valkyrienskies.mod.common.physics.management.physo.PhysicsObject;
import org.valkyrienskies.mod.common.physics.management.physo.ShipData;
import org.valkyrienskies.mod.common.physmanagement.shipdata.QueryableShipData;

public class SpawnPhysObjMessageHandler implements IMessageHandler<SpawnPhysObjMessage, IMessage> {
    @Override
    public IMessage onMessage(SpawnPhysObjMessage message, MessageContext ctx) {
        IThreadListener mainThread = Minecraft.getMinecraft();
        mainThread.addScheduledTask(() -> {
            if (Minecraft.getMinecraft().world != null) {
                // Spawn a PhysicsObject from the ShipData.
                World world = Minecraft.getMinecraft().world;
                QueryableShipData queryableShipData = QueryableShipData.get(world);
                ShipData toSpawn = message.shipToSpawnData;
                queryableShipData.addOrUpdateShipPreservingPhysObj(toSpawn);
                if (toSpawn.getPhyso() != null) {
                    throw new IllegalStateException("Wtf you can't spawn a ship twice!");
                }
                // Create a new PhysicsObject based on the ShipData.
                PhysicsObject physicsObject = new PhysicsObject(world, toSpawn, false);
                toSpawn.setPhyso(physicsObject);
                queryableShipData.addOrUpdateShipPreservingPhysObj(toSpawn);
            }
        });

        return null;
    }
}
