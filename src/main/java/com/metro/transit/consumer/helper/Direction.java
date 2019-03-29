/************************************
# 	Copyright © India 2019 		  	*
# 	All rights reserved.       		*
#***********************************/
package com.metro.transit.consumer.helper;

/**
* <h1>Direction.java</h1>
* Java Enum used to define direction and their corresponding ID.
* <p>
* @author  Manikandan Janarthanan
* @version 1.0
* @since   2019-03-28 22:48:52 IST
*/
enum Direction {
	south(1), east(2), west(3), north(4);

    private final int directionId;

    Direction(int directionId) {
        this.directionId = directionId;
    }

    public int getDirectionId() {
        return directionId;
    }
}
