package protocolsupport.protocol.core.initial;

import io.netty.channel.Channel;
import protocolsupport.api.ProtocolVersion;

public class Ping152ResponseTask implements Runnable {

	private InitialPacketDecoder initialDecoder;
	private Channel channel;

	public Ping152ResponseTask(InitialPacketDecoder initialDecoder, Channel channel) {
		this.initialDecoder = initialDecoder;
		this.channel = channel;
	}

	@Override
	public void run() {
		try {
			initialDecoder.setProtocol(channel, initialDecoder.receivedData, ProtocolVersion.MINECRAFT_1_5_2);
		} catch (Throwable t) {
			channel.close();
		}
	}

}