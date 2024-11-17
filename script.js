$(document).ready(function() {
    $(document).keypress(function(event) {
        // Get a random letter from A-Z
        let randomLetter = String.fromCharCode(65 + Math.floor(Math.random() * 26));

        // Create a new div with the random letter
        let bubble = $('<div class="bubble"></div>').text(randomLetter);

        // Append the bubble to the game area
        $('#game-area').append(bubble);

        // Animate the bubble (e.g., falling effect)
        bubble.animate({
            top: '80vh',
            opacity: 0
        }, 2000, function() {
            // Remove the bubble after animation is complete
            $(this).remove();
        });
    });
});
