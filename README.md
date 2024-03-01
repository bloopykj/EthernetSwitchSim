# EthernetSwitchSim
# This program looks to simulate the behavior of an 8 port ethernet switch and its ability to forward frames to ports while simultaneously updating its routing table containing known MAC addresses and their corresponding ports.
# port_addresses is a hashmap with the port numbersand the MAC addresses, and port_frmes is a hashmap with the port numbers and the frames it recieved.
# Routing table is empty by default and will be updated with the source port in which the frame is being sent from.
# It reads a file line-by-line called re.txt which contains: frame number, the port it is coming from, source MAC address, and destination MAC address. A port can have multiple MAC addresses.
# It will then check the two hashmaps if the MAC address is known, if yes, it will send the frame to that port only and save the source port and source MAC address. If not, it will broadcast the frame to all ports.
# When the program is done reading re.txt, it will then output the list of ports and the frames each recieved to a file named rw.txt.
