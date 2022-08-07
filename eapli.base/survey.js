const multipleMap = new Map();
let single_counter = 0;
let actual_section = 0;
let actual_question = 0;

var last_selected;  // used for single options

var actual_questions = [];
var bar_skip = 10;
var survey_flow = "Lets Start..."

var actual_bar = 0;
const max_bar_size = 270;

let questionnaire_line = 3;

var mandatory = Boolean(true);
var selected = Boolean(false);

var option_selected = ""

var multiple_selected = 0;
var scale_select = "";

let block_index = 0; // to start in the first section
let output_string  = ""

const questionnaire = "PRPP22-01\n\nProduct Questionnaire\n\nHello,\nThis questionnaire aims to better understand people perception of the price of a product\nIt takes approximately 15 minutes\nThank you very much for your time and support.\n\n1\nProduct Price\nQuestions about product price perception\n[mandatory]\nyes\n\n1\nHow familiar are you with product?\nSingle-Choice\n[mandatory]\n(answer honestly)\n[] I use daily;\n[] I have bought several times;\n[] In general I am aware of the product;\n[] I was not aware of such a product;\n\n2\nIf you knew that the product was priced, would you pay more or less to buy it?\nSingle-Choice with input value\n[mandatory]\n(no information to provide)\n[] 10 to 20 more;\n[] 5 to 10 more;\n[] No more no less;\n[] 5 to 10 less;\n[] 0 to 20 less;\n[] Other;\n\n3\nAbout how many units of product more or less would you buy?\n[answer honestly]\nScaling Options\n[mandatory]\n(consider next year, at each point of the listed price.)\n4 less|1 to 3 less|1 to 3 more|4 more\n- 20;\n- 10;\n- 5;\n- 3;\n\n4\nWhat would you expect to pay for product?\nNumeric\n[optional]\n(type the number that you expect)\n\n5\nAt what price would product start being so cheap that you would feel that the quality cant be good?\nSingle-Choice\n[mandatory]\n(these are only price range examples)\n[] 10;\n[] 25;\n[] 7;\n[] 12;\n[] 50;\n\n2\nProduct Review\nQuestions about some products review\n[optional]\nyes\n\n1\nBased on your experience, can you apply the following attributes to product?\nScaling Options\n[optional]\n(select one of the scales available)\nDefinitely|Probably|Probably not|Definitely not|Not sure\n- High quality;\n- Fair price;\n- A brand I trust;\n- Well built;\n- Good packaging;\n- High value;\n\n2\nTell us another reason why you would buy the product, other than the product itself?\nMultiple Choice with input value\n[optional]\n(you can select more than one option)\n[] Experience as a customer;\n[] Sales or Service Representative;\n[] Advertising;\n[] Warranty;\n[] Packaging;\n[] Trends;\n[] Other;\n\n3\nWhat do you like least about the product?\nFree-Text\n[optional]\n(write whatever you think)\n\nYou have successfully completed the questionnaire.Thank you for your help."

let questionnaire_title = "";
let welcome_message = "";
const sections = new Map(); // key is the section || value is the number of questions
let current_number = -1;

function build_survey() {

    write_to_output()
    block_index++
    write_to_output()
    block_index++
    write_to_output()
    block_index++


    const array = questionnaire.split("\n\n");
    questionnaire_title = array[1];
    welcome_message = array[2];

    console.log(questionnaire_line);

    // Start reading
    while (questionnaire_line <= array.length - 1) {
        let block = array[questionnaire_line];


        if (block.includes("\n")) {
            let block_content = block.split("\n")
            let number = block_content[0];
            let line = block_content[1];

            if (block_content[1].includes("?")) {
                console.log("Question\n")
                console.log(block + "\n")

                let q1 = {
                    question_id: "",
                    question: "",
                    instruction: "",
                    type: "",
                    obligatoriness: "",
                    repeatability: "",
                    extra_info: "",
                    scaling: []
                };

                q1.question_id = number;
                q1.question = line;
                let options_index;
                if (block_content[2].includes("[")) {
                    // instruction exists
                    q1.instruction = block_content[2];
                    q1.type = block_content[3];
                    q1.obligatoriness = block_content[4];
                    q1.extra_info = block_content[5];
                    options_index = 6;
                } else {
                    q1.type = block_content[2];
                    q1.obligatoriness = block_content[3];
                    q1.extra_info = block_content[4];
                    options_index = 5;
                }

                let opt_index = 0;
                const all_options = [];


                if (q1.type.includes("Scaling")) {
                    let line = block_content[options_index];
                    q1.scaling = line.split("|")
                    options_index++;
                }


                while (options_index <= block_content.length - 1) {
                    // see type of questions
                    if (q1.type.includes("Single") || q1.type.includes("Multiple")) {
                        let opt = block_content[options_index];
                        options_index++;
                        opt = opt.replace("[] ", "");
                        opt = opt.replace(";", "");

                        all_options[opt_index] = opt;

                        opt_index++;
                    } else if (q1.type.includes("Scaling")) {
                        let opt = block_content[options_index];
                        options_index++;
                        opt = opt.replace("- ", "");
                        opt = opt.replace(";", "");

                        all_options[opt_index] = opt;

                        opt_index++;
                    }
                }
                //console.log("Questions: \n");
                //console.log(Array.from(sections)[0][1]);
                Array.from(sections)[current_number][1].set(q1, all_options);
            } else {
                build_section(block_content);
            }
        } else {
            console.log("End of questionnaire\n")
            console.log(block + "\n")
        }
        questionnaire_line++;
    }

    console.log("\n\nSections:\n\n")
    console.log(sections)

    //Change values
    change_values();
}

function build_section(block_content) {
    console.log("Section\n")
    let s1 = null;
    if (block_content.length === 5) {
        s1 = {
            section_id: block_content[0],
            section_title: block_content[1],
            section_description: block_content[2],
            obligatoriness: block_content[3],
            repeatability: block_content[4]
        };
    } else {
        s1 = {
            section_id: block_content[0],
            section_title: block_content[1],
            obligatoriness: block_content[2],
            repeatability: block_content[3]
        };
    }
    const questions = new Map(); // key is the section || value is the number of questions
    sections.set(s1, questions);
    current_number++;
    console.log(s1)
}

function change_values() {

    const board = document.getElementById("content");
    const empty_section = "<div class=\"section\">\n" +
        "            <div class=\"section_id\" id=\"section_id\"></div>\n" +
        "            <div class=\"section_title\" id=\"section_title\"></div>\n" +
        "            <div class=\"section_info\" >\n" +
        "                <div class=\"section_description\" id=\"section_description\">\n" +
        "                    \n" +
        "                </div>\n" +
        "                <div class=\"section_obligatory\" id=\"section_obligatory\"></div>\n" +
        "            </div>\n" +
        "            <div class=\"start_section\" onclick=\"get_section_questions()\">Start</div>\n" +
        "        </div>";

    if (actual_question === 0) {
        // the survey has started and the section needs to be shown
        board.innerHTML = empty_section;
        document.getElementById("section_id").innerHTML = "Section " + Array.from(sections)[actual_section][0].section_id;
        document.getElementById("section_title").innerHTML = Array.from(sections)[actual_section][0].section_title;
        document.getElementById("section_description").innerHTML = Array.from(sections)[actual_section][0].section_description;
        document.getElementById("section_obligatory").innerHTML = Array.from(sections)[actual_section][0].obligatoriness.replace("[", "").replace("]", "").toUpperCase();
        write_to_output()
    }

}

function single_clicked(element) {
    if (single_counter === 0) {
        element.style.backgroundColor = "rgb(202,235,223)";
        last_selected = element;
    } else {
        last_selected.style.backgroundColor = "transparent"
        element.style.backgroundColor = "rgb(202,235,223)";
        last_selected = element;
    }
    single_counter++;
    selected = Boolean(true)
    option_selected = element.innerHTML
    console.log(option_selected)
}

function multiple_clicked(element) {
    if (!multipleMap.has(element.innerHTML)) {
        multipleMap.set(element.innerHTML, 0);
    }
    let counter = multipleMap.get(element.innerHTML);
    element.style.transition = "0s";
    if (counter % 2 === 0) {
        multiple_selected++;
        element.style.backgroundColor = "rgb(112,215,178)";
        element.id = "multiple_selected"
    } else {
        if (multiple_selected > 0) {
            multiple_selected--;
            element.id = ""
        }
        element.style.backgroundColor = "rgb(199,199,199)";
        element.id = ""
    }
    counter++;
    multipleMap.set(element.innerHTML, counter)
    element.style.transition = "0.8s";
    option_selected = element.innerHTML
    console.log(option_selected)
}

function get_section_questions() {
    actual_questions = Array.from(Array.from(sections)[actual_section][1]);

    //console.log("bar skip\n" + max_bar_size/actual_questions[1].length)
    bar_skip = max_bar_size / actual_questions.length;
    actual_bar += bar_skip

    //console.log(actual_questions)
    show_questions();
}

function show_questions() {
    const board = document.getElementById("content");
    board.innerHTML = "<div class=\"survey\" id=\"survey\">\n" +
        "        <div class=\"survey_flow\" id=\"survey_flow\">" + survey_flow + "</div>\n" +
        "        <div class=\"bar\">\n" +
        "            <div class=\"green-bar\" id=\"green-bar\"></div>\n" +
        "        </div>\n" +
        "        <div class=\"question\" id=\"question\">\n" +
        "            \n" +
        "        </div>\n" +
        "    </div>";
    document.getElementById("green-bar").style.width = actual_bar;
    const question_object = actual_questions[actual_question][0];
    const question_options = actual_questions[actual_question][1];
    document.getElementById("question").innerHTML = question_object.question;

    if (question_object.obligatoriness.includes("mandatory")) {
        mandatory = Boolean(true);
    } else {
        mandatory = Boolean(false);
    }

    if (question_object.type.includes("Single")) {
        let all_opts = "";
        document.getElementById("survey").innerHTML += "<div class=\"options\" id=\"options\">\n</div>";
        for (let i = 0; i < question_options.length; i++) {
            if (question_options[i].includes("Other")) {
                all_opts = all_opts + "<div class=\"option\" onclick=\"single_clicked(this)\">\n" +
                    "                <input class=\"other_option\" placeholder=\"Other\">\n" +
                    "            </div>"
            } else {
                all_opts = all_opts + "<div class=\"option\" onclick=\"single_clicked(this)\">" + question_options[i] + "</div>\n";
            }
        }
        document.getElementById("options").innerHTML = all_opts
    } else if (question_object.type.includes("Multiple")) {
        let all_opts = "";
        document.getElementById("survey").innerHTML += "<div class=\"multiple_options\" id=\"multiple_options\">\n</div>";
        for (let i = 0; i < question_options.length; i++) {
            if (i % 2 === 0) {
                if (question_options[i].includes("Other")) {
                    all_opts = all_opts + "<div class=\"up\">\n" +
                        "                <div class=\"multiple_option\" onclick=\"multiple_clicked(this)\">" + "<input class=\"other_option\" id=\"other_option\" placeholder=\"Other\">\n" + "</div>\n" +
                        "            </div>";
                }else {
                    all_opts = all_opts + "<div class=\"up\">\n" +
                        "                <div class=\"multiple_option\" onclick=\"multiple_clicked(this)\">" + question_options[i] + "</div>\n" +
                        "            </div>";
                }
            } else {
                if (question_options[i].includes("Other")) {
                    all_opts = all_opts + "<div class=\"down\">\n" +
                        "                <div class=\"multiple_option\" onclick=\"multiple_clicked(this)\">" + "<input class=\"other_option\" id=\"other_option\" placeholder=\"Other\">\n" + "</div>\n" +
                        "            </div>";
                }else {
                    all_opts = all_opts + "<div class=\"down\">\n" +
                        "                <div class=\"multiple_option\" onclick=\"multiple_clicked(this)\">" + question_options[i] + "</div>\n" +
                        "            </div>";
                }
            }
        }
        document.getElementById("multiple_options").innerHTML = all_opts
    } else if (question_object.type.includes("Scaling")) {
        let all_opts = "";
        for (let j = 0; j < question_object.scaling.length; j++) {
            all_opts = all_opts + "<div class=\"scale_unselected\" id=\"" + j + "\" onclick=\"scale_selected(this)\">" + (j + 1) + "</div>";
        }
        for (let i = 0; i < question_options.length; i++) {
            const question_id = "scale_question" + i;
            const id = "scaling_question" + i;
            const id1 = "scaling_options" + i;
            const id2 = "scales" + i;
            const id3 = "scale_selected_text" + i;

            document.getElementById("survey").innerHTML += "<div class=\"scaling_question\" id=\"" + id + "\">\n</div>";
            document.getElementById(id).innerHTML += "<div class=\"question scalo\" id=\"" + question_id + "\">" + question_options[i] + "</div>"
            document.getElementById(id).innerHTML += "<div class=\"scaling_options\" id=\"" + id1 + "\">\n</div>";
            document.getElementById(id1).innerHTML += "<div class=\"scales\" id=\"" + id2 + "\">\n</div>";
            document.getElementById(id2).innerHTML = all_opts
            document.getElementById(id).innerHTML += "<div class=\"scale_selected_text\" id=\"" + id3 + "\"></div>\n"
        }
    } else if (question_object.type.includes("Free-Text")) {
        document.getElementById("survey").innerHTML += "<div class=\"option\">\n" +
            "                <input class=\"other_option\" id=\"free_text\" placeholder=\"Your text here\">\n" +
            "            </div>"
    } else if (question_object.type.includes("Numeric")) {
        document.getElementById("survey").innerHTML += "<div class=\"option\">\n" +
            "                <input class=\"other_option\" id=\"numeric\" min=\"0\" type=\"number\" placeholder=\"Type the number here\">\n" +
            "            </div>"
    }

    document.getElementById("survey").innerHTML += "<div class=\"next_question\" onclick=\"next_question_section()\">Next</div>\n";
}

function next_question_section() {

    // Check if all mandatory questions were answered

    var already_shown = Boolean(false)

    //write_to_output()

    const question_object = actual_questions[actual_question][0];
    if (question_object.type.includes("Free-Text")){
        const val = document.getElementById("free_text").value;
        if (val.length===0 && question_object.obligatoriness.includes("mandatory")===true){
            alert("This question is mandatory, you must answer!")
            already_shown = Boolean(true)
        }
    }else if (question_object.type.includes("Numeric")){
        const val = document.getElementById("numeric").value;
        if (val.length===0 && question_object.obligatoriness.includes("mandatory")===true){
            alert("This question is mandatory, you must answer with a number!")
            already_shown = Boolean(true)
        }else {
            if (question_object.obligatoriness.includes("mandatory")===true){
                mandatory = true;
                selected = true;
            }else {
                mandatory = false;
            }
        }
    }else if (question_object.type.includes("Scaling")){
        const scaling_questions = document.getElementsByClassName("scaling_question").length;
        const scaling_questions_answered = document.getElementsByClassName("scale_selected").length
        if (scaling_questions!==scaling_questions_answered && question_object.obligatoriness.includes("mandatory")===true){
            alert("You must answer all the questions, it is mandatory!")
            already_shown = Boolean(true)
        }else {
            if (question_object.obligatoriness.includes("mandatory")===true){
                mandatory = true;
                selected = true;
            }else {
                mandatory = false;
            }
        }
    }


    if ((mandatory === true && selected === true) || mandatory === false || (mandatory === true && multiple_selected > 0)) {

        block_index++
        register_answer()

        questionnaire_line++;
        multiple_selected = 0;
        if (actual_question < actual_questions.length - 1) {
            actual_bar += bar_skip;
            document.getElementById("green-bar").style.width = actual_bar;
            actual_question++;
            survey_flow = "One More..."
            selected = Boolean(false)
            const question_object = actual_questions[actual_question][0];
            if (question_object.obligatoriness.includes("condition dependent")){
                const rule = question_object.obligatoriness.split(" ")[3]
                const response = question_object.obligatoriness.split(" ")[5].replace("]","")
                console.log(rule)
                console.log(response)
                const sec = rule.split("q")[0].replace("s","")
                const ques = rule.split("q")[1]
                var section_questions = Array.from(Array.from(sections)[sec-1][1])
                var question_new_object = section_questions[ques-1][0];
                console.log(question_new_object)
            }else {
                show_questions();
            }
        } else if (actual_section < sections.size - 1) {

            block_index++

            actual_section++;
            actual_question = 0;
            survey_flow = "Lets Start..."
            bar_skip = max_bar_size / actual_questions.length;
            actual_bar = 0
            document.getElementById("green-bar").style.width = actual_bar;
            selected = Boolean(false)
            change_values();
        }else {
            block_index++
            write_to_output()

            document.getElementById("content").innerHTML = "<div class=\"end_section\">\n" +
                "            <div class=\"success\">\n" +
                "                You have <span class=\"success_message\">successfully</span> completed the questionnaire.\n" +
                "            </div>\n" +
                "            <img src=\"https://i.ibb.co/3T42Njw/thumbs-thumbs-up-kid.gif\" style=\"width: 200px;margin-top: 10px;border-radius: 20px;\">\n" +
                "            <div class=\"success\" style=\"margin-top: 20px\">\n" +
                "                Thank you for your help." +
                "            </div>" +
                "        </div>"
            console.log(output_string)
            showContent()
            setTimeout(window.close, 3000)
        }
    } else {
        if (!already_shown){
            alert("This question is mandatory, you must answer!")
        }
    }
}

function scale_selected(element) {
    try {
        document.getElementById(element.parentElement.id).getElementsByClassName("scale_selected")[0].className = "scale_unselected"
    } catch (e) {

    }

    element.className = "scale_selected"
    const question_object = actual_questions[actual_question][0];
    document.getElementById("scale_selected_text" + element.parentElement.id.replace("scales", "")).innerHTML = question_object.scaling[element.innerHTML - 1]
}

function write_to_output(){
    // used for sections
    output_string += questionnaire.split("\n\n")[block_index] + "\n\n"
}

function register_answer(){
    var question = actual_questions[actual_question][0]
    var type_question = question.type
    var answer;

    if (type_question.includes("Single-Choice")){

        const question_options = actual_questions[actual_question][1];

        if (selected === true){
            if (question_options.includes("Other")){
                var other_value = document.getElementsByClassName("other_option")[0].value
                if (other_value.length!==0){
                    answer = option_selected
                    output_string += questionnaire.split("\n\n")[block_index].split("[]")[0] + "[X] " + other_value + "\n\n"
                }else {
                    answer = option_selected
                    output_string += questionnaire.split("\n\n")[block_index].split("[]")[0] + "[X] " + answer + "\n\n"
                }
            } else {
                answer = option_selected
                output_string += questionnaire.split("\n\n")[block_index].split("[]")[0] + "[X] " + answer + "\n\n"
            }

        }else {
            output_string += questionnaire.split("\n\n")[block_index].split("[]")[0] + "[?] " + "no answer" + "\n\n"
        }
    }else if (type_question.includes("Multiple")){
        const multiples = document.getElementsByClassName("multiple_option")
        console.log(multiples.length)
        let ct = 0;
        for (let g=0; g<multiples.length; g++){
            if (multiples[g].id==="multiple_selected"){
                if (multiples[g].innerHTML.includes("other_option")){
                    var other_val = document.getElementsByClassName("other_option")[0].value
                    if (other_val.length!==0){
                        if (ct===0){
                            answer = option_selected
                            output_string += questionnaire.split("\n\n")[block_index].split("[]")[0] + "[X] " + other_val + "\n\n"
                        }else {
                            output_string += "[X] " + other_val + "\n"
                        }
                    }
                }else {
                    if (ct===0){
                        answer = option_selected
                        output_string += questionnaire.split("\n\n")[block_index].split("[]")[0] + "[X] " + multiples[g].innerHTML + "\n"
                    }else {
                        answer = option_selected
                        output_string += "[X] " + multiples[g].innerHTML + "\n"
                    }
                }
                ct++
            }
        }
        if (ct>0){
            output_string+="\n"
        }else {
            output_string += questionnaire.split("\n\n")[block_index].split("[]")[0] + "[?] " + "no answer" + "\n\n"
        }
    }
    else if (type_question.includes("Scaling")){
        const scales = document.getElementsByClassName("scale_selected")
        if (scales.length>0){
            let ct = 0;
            for (let g=0; g<scales.length; g++){
                var id = scales[g].parentElement.id.replace("scales","")
                console.log(id)
                var question = document.getElementById("scale_question" + id).innerHTML
                console.log(question)
                var selected_text = document.getElementById("scale_selected_text" + id).innerHTML
                console.log(selected_text)

                if (ct===0){
                    output_string += questionnaire.split("\n\n")[block_index].split("[]")[0] + "\n[-] " + question + " -> " + selected_text + "\n"
                }else {
                    output_string += "[-] " + question + " -> " + selected_text + "\n"
                }
                ct++
            }
            output_string += "\n"
        }else {
            output_string += questionnaire.split("\n\n")[block_index].split("[]")[0] + "\n[?] " + "no answer" + "\n\n"
        }

    }
    else if (type_question.includes("Free-Text")){
        const val = document.getElementById("free_text").value;
        if (val.length!==0){
            output_string += questionnaire.split("\n\n")[block_index] + "\n" + "[>] " + val + "\n\n"
        }else {
            output_string += questionnaire.split("\n\n")[block_index] + "\n[?] " + "no answer" + "\n\n"
        }
    }else if (type_question.includes("Numeric")){
        const val = document.getElementById("numeric").value;
        if (val!==''){
            output_string += questionnaire.split("\n\n")[block_index] + "\n" + "[>] " + val + "\n\n"
        }else {
            output_string += questionnaire.split("\n\n")[block_index] + "\n[?] " + "no answer" + "\n\n"
        }
    }
    else {
        output_string += questionnaire.split("\n\n")[block_index] + "\n\n"
    }
}

function export_answers(){
    document.getElementById("content").innerHTML+="<h1>\n" + "<?php echo \"This message is from server side.\" ?>"
    "</h1>"
}

function showContent() {
    const xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET","content.php?text="+encodeURIComponent(output_string),true);
    xmlhttp.send();
}
