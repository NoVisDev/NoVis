# dev's notes

- RelayServer -> manages it all, boss loop -> accept conns, worker loop -> handle em.
- relayserverinitializer -> builds pipeline for EACH client conn. use for customizing client experience later on??
  - need a framedecoder to turn it into a bytebuf, rn linebasedframedecoder
  - need stringdecoder and encoder
  - you can insert ur own classes too as long as they implement a simplechannelimboundhandler
- logger, create one for EACH file.
- private static final Logger logger = LoggerFactory.getLogger(MyClassName.class);
- bootstrap class seems to be a class where u slap on everything.

# when introducing new packets

CREATE A NEW PACKETDATA subclass
MAKE IT READABLE IN THE DECODER
PUT IT IN THE ENUM
MAKE A HANDLING FUNCTION IN BOTH CLIENT AND SERVER.