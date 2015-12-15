package protocolsupport.protocol.transformer.middlepacketimpl.v_1_5_1_6.clientbound.play;

import java.util.Collection;
import java.util.Collections;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleHeldSlot;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;

public class HeldSlot extends MiddleHeldSlot<Collection<PacketData>> {

	@Override
	public Collection<PacketData> toData(ProtocolVersion version) {
		PacketDataSerializer serializer = PacketDataSerializer.createNew(version);
		serializer.writeShort(slot);
		return Collections.singletonList(new PacketData(ClientBoundPacket.PLAY_HELD_SLOT_ID, serializer));
	}

}
