package com.quiz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.models.MapUserPaper;
import com.quiz.models.MapUserQuestionChoice;
import com.quiz.models.Option;
import com.quiz.models.Paper;
import com.quiz.models.Question;
import com.quiz.models.User;
import com.quiz.repository.MapUserPaperRepository;
import com.quiz.repository.MapUserQuestionChoiceRepository;
import com.quiz.repository.OptionRepository;
import com.quiz.repository.QuestionRepository;
import com.quiz.repository.UserRepository;

@Service
public class QuestionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaperServiceImpl.class);

	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	OptionRepository optionRepository;
	@Autowired
	MapUserQuestionChoiceRepository mapUserQuestionChoiceRepository;
	@Autowired
	MapUserPaperRepository mapUserPaperRepository;
	@Autowired
	UserRepository userRepository;

	public Map<String, Object> getQuestionOption(Integer paperId, Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Question> ques = questionRepository.findAllByFkPaperId(paperId);
		List<Integer> fkquestion = ques.stream().map(m -> m.getId()).collect(Collectors.toList());
		List<Option> opt = optionRepository.findAllByFkQuestionIdIn(fkquestion);
		List<Option> opt1;
		List<MapUserQuestionChoice> mapUserQuestionChoices = null;// mapUserQuestionChoiceRepository.findAllByFkPaperAndFkUser(paperId,userId);

		if (userId > 0) {
			map.put("mapuserpaper", mapUserPaperRepository.findAllByFkPaperAndFkUser(paperId, userId));
			mapUserQuestionChoices = mapUserQuestionChoiceRepository.findAllByFkPaperAndFkUser(paperId, userId);
			for (Question question : ques) {
				opt1 = opt.stream().filter(f -> f.getFkQuestionId().equals(question.getId())).collect(Collectors.toList());
				question.setOpt(opt1);
				MapUserQuestionChoice muqc = mapUserQuestionChoices.stream()
						.filter(f -> f.getFkQuestion().equals(question.getId())).findFirst()
						.orElseGet(MapUserQuestionChoice::new);
				question.setChoices(muqc);
			}
		} else {
			for (Question question : ques) {
				opt1 = opt.stream().filter(f -> f.getFkQuestionId().equals(question.getId())).collect(Collectors.toList());
				question.setOpt(opt1);
			}
			for (Option opts : opt) {
				opts.setIsCorrectChoice(null);
			}
		}

		map.put("ques", ques);
		return map;
	}

	@Transactional
	public void saveQuestionPaper(List<Question> ques, Paper paper) {

		questionRepository.saveAll(ques);
		List<Option> option = new ArrayList<>();
		for (Question q : ques) {
			for (Option o : q.getOpt()) {
				/*if (o.getIsCorrectChoice()) {
					q.setFkCorrectChoice(o.getId());
				}*/
				o.setFkQuestionId(q.getId());
				option.add(o);
			}

		}
		 optionRepository.saveAll(option);

		// questionRepository.flush();
		for (Question q : ques) {
			for (Option o : q.getOpt()) {
				if (o.getIsCorrectChoice()) {
					q.setFkCorrectChoice(o.getId());
					break;
				}
				 
			}
			questionRepository.updateFkCorrectChoiceById(q.getFkCorrectChoice(), q.getId());
		}

		List<User> users = (List<User>) userRepository.findAll();
		List<MapUserPaper> mup = new ArrayList<MapUserPaper>();

		for (User u : users) {
			mup.add(new MapUserPaper(u.getId(), paper.getId()));
		}

		mapUserPaperRepository.saveAll(mup);

	}

	public Boolean isValidForQuiz(Integer userid, Integer paperId) {

		return mapUserPaperRepository.existsByFkUserAndFkPaperAndMarksIsNull(userid, paperId);

	}

	public void saveUserQuestions(List<MapUserQuestionChoice> muqc) {

		if (muqc.size() > 0) {
			MapUserQuestionChoice m = muqc.get(0);
			List<Question> qu = questionRepository.findAllByFkPaperId(m.getFkPaper());
			Integer totalMarks = 0;
			for (MapUserQuestionChoice mqc : muqc) {
				Optional<Question> q = qu.stream().filter(f -> f.getId() == mqc.getFkQuestion()).findFirst();
				if (q.isPresent()) {
					if (mqc.getFkChoice() == q.get().getFkCorrectChoice()) {
						mqc.setMarks(q.get().getCorrectMark());

					} else {
						mqc.setMarks(q.get().getWrongMark());
					}
					totalMarks += mqc.getMarks();
				}
			}
			mapUserQuestionChoiceRepository.saveAll(muqc);
			mapUserPaperRepository.updateMarks(totalMarks, m.getFkUser(), m.getFkPaper());

		}

	}

}
