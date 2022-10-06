import SockJS from "sockjs-client";
import Stomp from "stompjs";
let url = "http://localhost:8009/api/socket";
let sockJS = new SockJS(url, null, {
  transports: ["websocket", "xhr-streaming", "xhr-polling"],
});

export let getStompClient = () => {
  return Stomp.over(sockJS);
};
