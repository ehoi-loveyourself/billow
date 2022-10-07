import SockJS from "sockjs-client";
import Stomp from "stompjs";

let url = "https://j7b309.p.ssafy.io/api/socket";

let sockJS = new SockJS(url, null, {
  transports: ["websocket", "xhr-streaming", "xhr-polling"],
});

export let getStompClient = () => {
  return Stomp.over(sockJS);
};
