# Ugh, Homework!

Thanks for all the time you've given us so far.  Unfortunately, there's one more
step: a small programming assignment.  We'd love to see what kind of code you 
write under normal conditions, without the stress and constraints of an 
interview.

This repository contains a bare-bones Spring Boot application that serves up 
a simple Angular UI.  Once you've cloned the repo (or downloaded it) you should
be able to run `gradlew bootRun` and, once the app is done booting, hit 
`http://localhost:8080` in your web browser to see the following screen:

![Alt text](https://s3.amazonaws.com/homework-public/homework+screen.png)

This is a simplified version of what a server in a restaurant would see as
they were entering an order.  It's pretty useless right now, so here's what
we'd like to add:

- If you click on one of the items (e.g., "Cobb Salad"), it should
get added to the list of items in the order on the right, and the numbers
underneath should change.
- If you click on item that's already on the ticket, it should add it again.
- If you click on an item already in the order and click "Void", it 
should remove one of the items from the order.
- There should be some way to clear the ticket and start over.

The app comes with a controller and DAO for generating the list of available
items.

Here's what we're looking for:

- Well-designed and organized code to implement REST services that the 
single-page app providing the page consumes.
- Code quality and correctness.  Please format, document, and test your code
like this was the real deal.
- How well you handle ambiguity: Are your decisions reasonable when you have
to "fill in the blanks" on your own?
- Fault tolerance, logging, and error handling (although we recognize there's not _that_ much room
for engineering on this!)

Here's what we don't care about:

- Which frameworks you use (other then Spring Boot, because we use it a lot)
- How well you know Spring Boot.
- Whitespace and other nit-picky formatting conventions.  We just want
something that looks like what a professional Java programmer would write.
- How whiz-bang and bleeding edge your front-end code is.
- Permanent storage.  Just do everything in memory.
- Your Gradle or Git proficiency.

Please add the above features to the sample app, and when you're done stick 
it up on github or some other place we can see it.  Thanks again for interviewing
with us!