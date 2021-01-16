(ns app.icons)

(defn copy [{:keys [class]}]
  [:svg {:xmlns "http://www.w3.org/2000/svg", :width "24", :height "24", :viewBox "0 0 24 24", :class class} [:path {:d "M11 8a2.997 2.997 0 00-3 3v9a2.997 2.997 0 003 3h9a2.997 2.997 0 003-3v-9a2.997 2.997 0 00-3-3zm0 2h9c.276 0 .525.111.707.293S21 10.724 21 11v9c0 .276-.111.525-.293.707S20.276 21 20 21h-9c-.276 0-.525-.111-.707-.293S10 20.276 10 20v-9c0-.276.111-.525.293-.707S10.724 10 11 10zm-6 4H4c-.276 0-.525-.111-.707-.293S3 13.276 3 13V4c0-.276.111-.525.293-.707S3.724 3 4 3h9c.276 0 .525.111.707.293S14 3.724 14 4v1a1 1 0 002 0V4a2.997 2.997 0 00-3-3H4a2.997 2.997 0 00-3 3v9a2.997 2.997 0 003 3h1a1 1 0 000-2z"}]])

(defn cross [{:keys [class]}]
  [:svg {:xmlns "http://www.w3.org/2000/svg", :width "24", :height "24", :viewBox "0 0 24 24", :class class} [:path {:d "M5.293 6.707L10.586 12l-5.293 5.293a.999.999 0 101.414 1.414L12 13.414l5.293 5.293a.999.999 0 101.414-1.414L13.414 12l5.293-5.293a.999.999 0 10-1.414-1.414L12 10.586 6.707 5.293a.999.999 0 10-1.414 1.414z"}]])

(defn github [{:keys [class]}]
  [:svg {:xmlns "http://www.w3.org/2000/svg", :width "24", :height "24", :viewBox "0 0 24 24", :class class} [:path {:d "M8.713 18.042c-1.268.38-2.06.335-2.583.17a2.282 2.282 0 01-.614-.302c-.411-.284-.727-.675-1.119-1.172-.356-.451-.85-1.107-1.551-1.476a2.694 2.694 0 00-.604-.232 1 1 0 10-.485 1.941c.074.023.155.06.155.06.252.133.487.404.914.946.366.464.856 1.098 1.553 1.579.332.229.711.426 1.149.564 1.015.321 2.236.296 3.76-.162a1 1 0 10-.575-1.915zM17 22v-3.792a4.377 4.377 0 00-.292-1.942c.777-.171 1.563-.427 2.297-.823 2.083-1.124 3.496-3.242 3.496-6.923a6.408 6.408 0 00-1.379-3.981 6.044 6.044 0 00-.293-3.933 1 1 0 00-.634-.564c-.357-.106-1.732-.309-4.373 1.362a14.24 14.24 0 00-6.646-.002C6.537-.267 5.163-.064 4.806.042a.998.998 0 00-.635.565 6.044 6.044 0 00-.292 3.932A6.414 6.414 0 002.5 8.556c0 3.622 1.389 5.723 3.441 6.859.752.416 1.56.685 2.357.867a4.395 4.395 0 00-.299 1.88L8 22a1 1 0 002 0v-3.87l-.002-.069a2.357 2.357 0 01.661-1.816 1 1 0 00-.595-1.688c-.34-.042-.677-.094-1.006-.159-.79-.156-1.518-.385-2.147-.733-1.305-.723-2.391-2.071-2.41-5.042.013-1.241.419-2.319 1.224-3.165a1 1 0 00.212-1.04 4.045 4.045 0 01-.14-2.392c.491.107 1.354.416 2.647 1.282a1 1 0 00.825.133 12.229 12.229 0 016.47.002.994.994 0 00.818-.135c1.293-.866 2.156-1.175 2.647-1.282a4.041 4.041 0 01-.141 2.392c-.129.352-.058.755.213 1.04a4.419 4.419 0 011.224 3.06c0 3.075-1.114 4.445-2.445 5.163-.623.336-1.343.555-2.123.7-.322.06-.651.106-.983.143a1 1 0 00-.608 1.689 2.36 2.36 0 01.662 1.837l-.003.078V22a1 1 0 002 0z"}]])

(defn trash [{:keys [class]}]
  [:svg {:xmlns "http://www.w3.org/2000/svg", :width "24", :height "24", :viewBox "0 0 24 24", :class class} [:path {:d "M18 7v13c0 .276-.111.525-.293.707S17.276 21 17 21H7c-.276 0-.525-.111-.707-.293S6 20.276 6 20V7zm-1-2V4a2.997 2.997 0 00-3-3h-4a2.997 2.997 0 00-3 3v1H3a1 1 0 000 2h1v13a2.997 2.997 0 003 3h10a2.997 2.997 0 003-3V7h1a1 1 0 000-2zM9 5V4c0-.276.111-.525.293-.707S9.724 3 10 3h4c.276 0 .525.111.707.293S15 3.724 15 4v1zm0 6v6a1 1 0 002 0v-6a1 1 0 00-2 0zm4 0v6a1 1 0 002 0v-6a1 1 0 00-2 0z"}]])

(defn upload [{:keys [class]}]
  [:svg {:xmlns "http://www.w3.org/2000/svg", :width "24", :height "24", :viewBox "0 0 24 24", :class class} [:path {:d "M20 15v4c0 .276-.111.525-.293.707S19.276 20 19 20H5c-.276 0-.525-.111-.707-.293S4 19.276 4 19v-4a1 1 0 00-2 0v4a2.997 2.997 0 003 3h14a2.997 2.997 0 003-3v-4a1 1 0 00-2 0zm-9-9.586V15a1 1 0 002 0V5.414l3.293 3.293a.999.999 0 101.414-1.414l-5-5a1.006 1.006 0 00-1.414 0l-5 5a.999.999 0 101.414 1.414z"}]])