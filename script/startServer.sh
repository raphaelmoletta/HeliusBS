#set -x
TITLE='\033[0;34m'
BODY='\033[1;34m'
RESPONSE='\033[0;37m'
NC='\033[0m'
echo -e "${TITLE}-> Welcome to HeliusBS <-"
echo -e "->---------------------<-"
echo -e "${BODY}->Starting Glassfish${RESPONSE}"
~/glassfish5/bin/asadmin start-domain
echo -e "${BODY}->Releasing WIFI card${RESPONSE}"
rfkill unblock all
echo -e "${BODY}->Setting WIFI ip${RESPONSE}"
ifconfig wlan0 10.0.0.1/24 up
echo -e "${BODY}->Configuring NAT${RESPONSE}"
iptables -t nat -F
iptables -F
iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
iptables -A FORWARD -i wlan0 -o eht0 -j ACCEPT
echo -e "${BODY}->Setting ip forward${RESPONSE}"
echo -e '1' > /proc/sys/net/ipv4/ip_forward
echo -e "${BODY}->Starting DNSMASQ${RESPONSE}"
dnsmasq -C /etc/dnsmasq.conf
echo -e "${BODY}->Starting HOSTAPD${RESPONSE}"
hostapd -B /etc/hostapd/hostapd.conf
echo -e "${NC}"
