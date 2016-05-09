package protocolsupport.protocol.packet.middlepacketimpl.clientbound.play.v1_5_1_6_1_7;

import net.minecraft.server.v1_9_R1.EnumParticle;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.packet.ClientBoundPacket;
import protocolsupport.protocol.packet.middlepacket.clientbound.play.MiddleWorldParticle;
import protocolsupport.protocol.packet.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.id.IdRemapper;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class WorldParticle extends MiddleWorldParticle<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_WORLD_PARTICLES_ID, version);
		EnumParticle particle = EnumParticle.values()[type];
		String name = particle.b();
		switch (particle) {
			case ITEM_CRACK: {
				name += IdRemapper.ITEM.getTable(version).getRemap(adddata.get(0));
				break;
			}
			case BLOCK_CRACK:
			case BLOCK_DUST: {
				int blockstateId = adddata.get(0);
				name += "_" + (IdRemapper.BLOCK.getTable(version).getRemap((blockstateId & 4095) << 4) >> 4) + "_" + ((blockstateId >> 12) & 0xF);
				break;
			}
			default: {
				break;
			}
		}
		serializer.writeString(name);
		serializer.writeFloat(x);
		serializer.writeFloat(y);
		serializer.writeFloat(z);
		serializer.writeFloat(offX);
		serializer.writeFloat(offY);
		serializer.writeFloat(offZ);
		serializer.writeFloat(speed);
		serializer.writeInt(count);
		return RecyclableSingletonList.create(serializer);
	}

}