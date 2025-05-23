export class KrakenService {

    private socket: WebSocket;
    private state: boolean;
    private onReady: (() => void) | undefined;
    private observers:((data : any) => void)[];

    constructor() {
        this.socket = new WebSocket("wss://ws.kraken.com/v2");
        this.state = false;
        this.observers = [];
        
        this.socket.onopen = () => {
            this.state = true;
            this.sendRequest();
        }
        this.socket.onmessage = (data) => {
            this.observers.forEach(observer => observer(data));
        }
        this.socket.onclose = () => {
            this.state = false;
        } 
    }

    private sendRequest() {

        this.socket.send(JSON.stringify({
            method: "subscribe",
            params: {
                channel: "ticker",
                symbol: [
                "XBT/USD",
                "USDT/USD",
                "ETH/USD",
                "XRP/USD",
                "USDC/USD",
                "DOGE/USD",
                "ADA/USD",
                "SOL/USD",
                "DOT/USD",
                "LTC/USD",
                "BCH/USD",
                "LINK/USD",
                "XLM/USD",
                "TRX/USD",
                "EOS/USD",
                "ETC/USD"
             ],
            } ,
        }));

        
    }

    addObserver(observer : (data: any) => void) {
        this.observers.push(observer);
    }


}